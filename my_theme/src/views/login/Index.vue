<template>
    <section-login
    >
    </section-login>
</template>

<script>
  // Extensions
  import View from '@/views/View'
  import SectionLogin from '@/views/sections/Login.vue'

  // Mixins
  import LoadSections from '@/mixins/load-sections'

  export default {
    name: 'Login',

    metaInfo: { title: '登录' },

    extends: View,

    components: {
      SectionLogin,
    },

    props: {
      id: {
        type: String,
        default: 'login',
      },
    },

    methods: {
      doSth: function (data) {
        alert('aaaasssxxx')
        this.$emit('another')
      },
    },
  }
</script>
