
<script>
  // Extensions
  import View from '@/views/View'

  // Mixins
  import LoadSections from '@/mixins/load-sections'

  export default {
    name: 'SearchResult',

    metaInfo: { title: '搜索结果' },

    extends: View,

    mixins: [
      LoadSections([
        'hero-alt',
        'search-result',
      ]),
    ],

    props: {
      id: {
        type: String,
        default: 'search-result',
      },
    },
  }
</script>
