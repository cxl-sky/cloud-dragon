<template>
  <my-iframe name="kibana" src="http://localhost:5601/app/kibana#/discover"/>
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
