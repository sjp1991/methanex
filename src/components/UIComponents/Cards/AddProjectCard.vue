<template>
  <div class="card">
    <div style="font-size:1000%;width:100%;text-align:center" v-on:click="createNewProject">+</div>
    <div class="card-footer" v-if="$slots.footer">
      <hr>
      <slot name="footer"></slot>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'add-project-card',
    props: {
      portfolioId: {
          default: -1
      }
    },
    methods: {
      createNewProject() {
        this.$router.push({path: `/admin/addProjects/${this.portfolioId}`});
      }
    }
  }

</script>
<style scoped>
    div.card {
        width: 30%;
    }
    div.body-data {
        padding-top: 0.5em;
        padding-bottom: 0.5em;
    }
</style>
