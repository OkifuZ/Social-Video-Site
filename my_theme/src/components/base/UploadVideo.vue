<template>
  <v-theme-provider dark>
    <base-section
      id="contact-us"
      class="primary"
      space="36"
    >
      <v-row
        align="center"
        class="ma-0"
        justify="center"
      >
        <base-title
          space="0"
          title="请输入视频的URL喵"
        />

        <v-responsive
          class="px-4 mx-6"
          max-width="600"
          width="100%"
        >
          <base-text-field
            hide-details
            label="视频url"
          >
          </base-text-field>
        </v-responsive>
      </v-row>
    </base-section>
  </v-theme-provider>
</template>

<script>
  export default {
    name: 'BaseUploadVideoUrl',
  }
</script>
