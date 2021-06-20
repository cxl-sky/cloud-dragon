<template>
  <iframe :src="src"></iframe>
</template>

<script>
export default {
  name: "index",
  mounted(options) {
    console.log(options)
  },
  data () {
    return {
      src: '你的src'
    }
  }
}
</script>

<style scoped>

</style>
