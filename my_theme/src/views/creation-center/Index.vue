
<script>
  // Extensions
  import View from '@/views/View'

  // Mixins
  import LoadSections from '@/mixins/load-sections'

  export default {
    name: 'Creation Center',

    metaInfo: { title: '创作中心' },

    extends: View,

    mixins: [
      LoadSections([
        'hero-alt',
        'creation',
      ]),
    ],

    props: {
      id: {
        type: String,
        default: 'creation-center',
      },
    },
  }
</script>
