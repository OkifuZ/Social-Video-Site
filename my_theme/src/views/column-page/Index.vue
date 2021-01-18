
<script>
  // Extensions
  import View from '@/views/View'

  // Mixins
  import LoadSections from '@/mixins/load-sections'

  export default {
    name: '阅读专栏',

    metaInfo: { title: '阅读专栏' },

    extends: View,

    mixins: [
      LoadSections([
        'hero-alt',
        'column',
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
