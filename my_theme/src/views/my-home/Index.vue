
<script>
  // Extensions
  import View from '@/views/View'

  // Mixins
  import LoadSections from '@/mixins/load-sections'

  export default {
    name: 'Home',

    metaInfo: { title: '欢迎回家' },

    extends: View,

    mixins: [
      LoadSections([
        'hero-alt',
        'bangumis-row',
        'videos-row',
        'special-column',
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
