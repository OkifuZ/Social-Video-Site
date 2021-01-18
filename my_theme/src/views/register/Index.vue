<

<script>
  // Extensions
  import View from '@/views/View'
  import SectionLogin from '@/views/sections/Login.vue'

  // Mixins
  import LoadSections from '@/mixins/load-sections'

  export default {
    name: 'Register',

    metaInfo: { title: '注册' },

    extends: View,

    components: {
      SectionLogin,
    },

    mixins: [
      LoadSections([
        'register',
      ]),
    ],

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
