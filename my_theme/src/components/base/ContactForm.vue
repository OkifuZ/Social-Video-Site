<template>
  <div>
    <base-info-card
      :title="title"
      :subtitle="subtitle"
      space="4"
      color="primary"
    />

    <base-text-field label="视频的标题..." />

    <base-text-field label="视频的URL..." />

    <base-text-field label="视频封面的URL" />

    <base-textarea
      class="mb-6"
      label="写一些视频的简介吧..."
    />

    <base-btn
      :color="!theme.isDark ? 'accent' : 'white'"
      outlined
      target="_blank"
    >
      发布视频
    </base-btn>
  </div>
</template>

<script>
  export default {
    name: 'BaseContactForm',

    // Injected from the Vuetify Themeable mixin
    inject: ['theme'],

    props: {
      subtitle: String,
      title: {
        type: String,
        default: 'MAIL US YOUR MESSAGE',
      },
    },
  }
</script>
