<script>
  // Extensions
  import View from '@/views/View'

  // Mixins
  import LoadSections from '@/mixins/load-sections'

  export default {
    name: '刷番刷番~~~',

    metaInfo: { title: '刷番刷番~' },

    extends: View,

    mixins: [
      LoadSections([
        'hero-alt',
        'bangumi-player',
      ]),
    ],

    props: {
      id: {
        type: String,
        default: 'about',
      },
    },
  }
</script>
