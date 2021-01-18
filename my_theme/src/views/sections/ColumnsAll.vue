<template>
  <base-section id="special-column">
    <base-section-heading title="所有的专栏都在这里">
      随便点点看看吧
    </base-section-heading>

    <v-container>
      <v-row>
        <v-col
          v-for="(feature, i) in columns"
          :key="i"
          cols="12"
          md="6"
        >
          <base-avatar-card
            v-bind="transform(feature)"
            :icon="feature.icon"
            :title="feature.columnName"
            align="left"
            horizontal
          >
            <div>
                <p>{{feature.columnAbstract}}
                </p>
                <v-btn
                    text
                    :to="'/personal-inf/'+feature.userId"
                >
                    作者: {{feature.nickname}}
                </v-btn>
                <v-divider
                  class="mx-4"
                  vertical
                ></v-divider>
                <v-btn
                    text
                    :to="'/column-page/'+feature.columnId"
                >
                    click to have a look
                </v-btn>
                <v-btn 
                        v-if="is_a_Admin()"
                        color = "error"
                        @click="deleteColumn(feature.columnId)"
                        flat
                      >
                      <h3>删除</h3>
                      <v-icon
                        dark
                        right
                      >
                    mdi-cancel
                  </v-icon>
                </v-btn>
            </div>
          </base-avatar-card>
        </v-col>
      </v-row>
    </v-container>
  </base-section>
</template>

<script>
  export default {
    name: 'SectionSpecialColumn',

    data: () => ({
      columns: null,
      test: {
          title: '安达与岛村漫画动漫之家有备份，快去下载呀',
          abstract: '众所周知，陈睿总有一天会被钉在耻辱柱上，回不到过去那便毁灭现实吧，叔叔他只是资本家，共产主义的幽灵不会退散',
          icon: 'mdi-message-text',
          user: 'Trump',
          userId: 'ashi7771',
          columnId: '78asf9dy',
        },
    }),
    created() {
      this.getColumns()
    },
    methods: {
      is_a_Admin: function() {
        return this.$cookies.get("isAdmin")
      },
      deleteColumn: function(columnId) {
        this.$axios
        .delete('admin/delete/columnId='+columnId, {            
          headers: {token: this.$cookies.get("token")}
        })
        .then(res => {
          if (res.data.code === 200) {
            this.$router.go(0)
          } else {
            alert(res.data.msg)
          }
        })
        .catch(failResponse => {
          alert('delete column response failed')
        })
      },
      getColumns: function() {
        this.$axios
        .get('/columns', {
        })
        .then(res => {
            this.columns = res.data
        })
        .catch(failResponse => {
            alert('columns get response failed')
        })
      },
      transform (col) {
        return {
          title: col.columnName,
          abstract: col.columnAbstract,
          icon: col.icon,
          user: col.nickname,
          userId: col.userId,
          columnId: col.columnId,
        }
      }
    },
  }
</script>
