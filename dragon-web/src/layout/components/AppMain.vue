<template>
  <div>
    <section :class="{'fixed-header':fixedHeader}"/>
    <section class="app-main">
      <transition name="fade-transform" mode="out-in">
        <router-view :key="key"/>
      </transition>
    </section>
  </div>
</template>

<script>
export default {
  name: 'AppMain',
  computed: {
    key() {
      return this.$route.path
    },
    fixedHeader() {
      return this.$store.state.settings.fixedHeader
    },
  }
}
</script>

<style scoped>
.app-main {
  /*50 = navbar  */
  min-height: calc(100vh - 50px);
  width: 100%;
  position: relative;
  overflow: hidden;
}

.fixed-header {
  height: 50px;
  width: 100%;
}
</style>

<style lang="scss">
// fix css style bug in open el-dialog
.el-popup-parent--hidden {
  .fixed-header {
    padding-right: 15px;
  }
}
</style>
