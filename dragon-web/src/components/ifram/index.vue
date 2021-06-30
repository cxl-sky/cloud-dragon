<template>
  <iframe class="iframe" :src="baseUrl + src" width="100%" height="100%" scrolling="No"
          onresize="noresize" :name="name"></iframe>
</template>

<script>
import {baseUrl} from '@/utils/request'

export default {
  name: "index",
  props: {
    src: {
      type: String,
      default: ''
    },
    name: {
      type: String,
      default: 'iframe'
    }
  },
  data() {
    return {
      // baseUrl: baseUrl
      baseUrl: ''
    }
  }
}
</script>

<style scoped>
.iframe {
  bottom: 0;
  position: absolute;
  border: 0;
}
</style>
