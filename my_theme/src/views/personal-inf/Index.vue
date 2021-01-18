
<script>
  // Extensions
  import View from '@/views/View'

  // Mixins
  import LoadSections from '@/mixins/load-sections'

  export default {
    name: 'Personal Information',

    metaInfo: { title: '个人信息' },

    extends: View,

    mixins: [
      LoadSections([
        'hero-alt',
        'user-inf',
      ]),
    ],

    props: {
      id: {
        type: String,
        default: 'personal-inf',
      },
    },
  }
</script>
