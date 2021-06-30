<template>
  <my-iframe name="nacos" src="/nacos"/>
</template>

<script>
import MyIframe from '@/components/ifram'

export default {
  name: "index",
  components: {
    MyIframe
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
