
<script>
  // Extensions
  import View from '@/views/View'

  // Mixins
  import LoadSections from '@/mixins/load-sections'

  export default {
    name: 'Chat',

    metaInfo: { title: '聊天室' },

    extends: View,

    mixins: [
      LoadSections([
        'chatting',
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
