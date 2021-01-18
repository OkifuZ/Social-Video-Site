
<script>
  // Extensions
  import View from '@/views/View'

  // Mixins
  import LoadSections from '@/mixins/load-sections'

  export default {
    name: 'message',

    metaInfo: { title: '查看动态' },

    extends: View,

    mixins: [
      LoadSections([
        'hero-alt',
        'moment',
      ]),
    ],

    props: {
      id: {
        type: String,
        default: 'home',
      },
    },
  }
</script>
