<script>
  // Extensions
  import View from '@/views/View'

  // Mixins
  import LoadSections from '@/mixins/load-sections'

  export default {
    name: '在看什么好东西',

    metaInfo: { title: '在看什么好东西' },

    extends: View,

    mixins: [
      LoadSections([
        'hero-alt',
        'video-player',
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
