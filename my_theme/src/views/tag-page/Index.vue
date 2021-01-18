
<script>
  // Extensions
  import View from '@/views/View'

  // Mixins
  import LoadSections from '@/mixins/load-sections'

  export default {
    name: '标签索引',

    metaInfo: { title: '标签索引' },

    extends: View,

    mixins: [
      LoadSections([
        'hero-alt',
        'tags',
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
