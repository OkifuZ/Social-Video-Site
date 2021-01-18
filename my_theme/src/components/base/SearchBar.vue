<template>
  <v-card
    class="mx-auto"
    max-width="800"
  >
    <v-sheet class="pink lighten-2">
        <v-text-field
            v-model="search"
            label="请输入关键字"
            dark
            flat
            append-icon="mdi-magnify"
            solo-inverted
            hide-details
            clearable
            background-color="pink lighten-3"
            clear-icon="mdi-close-circle-outline"
            @keydown.enter="getSearch"
        ></v-text-field>
    </v-sheet>
  </v-card>
</template>
<script>
  export default {
    data: () => ({
      search: null,
    }),
    methods: {
      getSearch: function () {
        var p = '/search-result/'+ this.search
        if (this.$route.path.includes('/search-result'))
        {
          this.$router.replace({ path: p })
          this.$router.go(0)
        }
        else {
          this.$router.replace({ path: p })
        }
      },
    },
  }
</script>
