import { asyncRoutes, constantRoutes } from '@/router'
import { fetchUserMenuList } from '@/api/menu'
import Layout from '@/layout'

/**
 * Use meta.role to determine if the current user has permission
 * @param roles
 * @param route
 */
function hasPermission(roles, route) {
  if (route.meta && route.meta.roles) {
    return roles.some(role => route.meta.roles.includes(role))
  } else {
    return true
  }
}

/**
 * Filter asynchronous routing tables by recursion
 * @param routes asyncRoutes
 * @param roles
 */
export function filterAsyncRoutes(routes, roles) {
  const res = []

  routes.forEach(route => {
    const tmp = { ...route }
    if (hasPermission(roles, tmp)) {
      if (tmp.children) {
        tmp.children = filterAsyncRoutes(tmp.children, roles)
      }
      res.push(tmp)
    }
  })

  return res
}

/**
 * 静态路由懒加载
 * @param view  格式必须为 xxx/xxx 开头不要加斜杠
 * @returns
 */
export const loadView = (view) => {
  return (resolve) => require([`@/views/${view}.vue`], resolve)
}

/**
 * 把从后端查询的菜单数据拼装成路由格式的数据
 * @param routes
 * @param data 后端返回的菜单数据
 */
export function generaMenu(routes, data) {
  console.log("generaMenu", data)
  data.forEach(item => {
    const menu = {
      path: item.url,
      component: item.component === '#' ? Layout : loadView(item.component),
      hidden: item.status === 0, // 状态为0的隐藏
      redirect: item.redirect,
      children: [],
      name: item.code,
      meta: {title:item.title, icon:item.icon}
    }

    if (item.children) {
      generaMenu(menu.children, item.children)
    }
    routes.push(menu)
  })
  console.log("generaMenu end", routes)

  return routes
}

const state = {
  routes: [],
  addRoutes: []
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes
    state.routes = constantRoutes.concat(routes)
  }
}

const actions = {
  generateRoutes({ commit }) {
    return new Promise(resolve => {
      fetchUserMenuList().then(res => {
        const menuData = Object.assign([], res.data)
        const tempAsyncRoutes = Object.assign([], asyncRoutes)
        const accessedRoutes = generaMenu(tempAsyncRoutes, menuData)

        commit('SET_ROUTES', accessedRoutes)
        resolve(accessedRoutes)
      }).catch(error => {
        console.log(error)
      })
      // let accessedRoutes
      // if (roles.includes('admin')) {
      //   accessedRoutes = asyncRoutes || []
      // } else {
      //   accessedRoutes = filterAsyncRoutes(asyncRoutes, roles)
      // }
      // commit('SET_ROUTES', accessedRoutes)
      // resolve(accessedRoutes)
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
