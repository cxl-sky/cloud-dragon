import defaultSettings from '@/settings'

const title = defaultSettings.title || 'Cloud Dragon'

export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    return `${pageTitle} - ${title}`
  }
  return `${title}`
}
