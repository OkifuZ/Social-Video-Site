
<script>
  // Extensions
  import View from '@/views/View'

  // Mixins
  import LoadSections from '@/mixins/load-sections'

  export default {
    name: 'message',

    metaInfo: { title: '收到的私信' },

    extends: View,

    mixins: [
      LoadSections([
        'hero-alt',
        'messages',
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
