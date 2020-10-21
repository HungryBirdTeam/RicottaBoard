<template>
  <div>
    <v-text-field
      v-model="dates"
      label="일정 설정"
      prepend-icon="mdi-calendar"
      readonly
      @click="isDatePicker=true"
    ></v-text-field>
    <v-date-picker
      class="date-modal"
      v-model="dates"
      range
      v-if="isDatePicker"
    ></v-date-picker>
  </div>
</template>

<script>
export default {
  data() {
    return{
      dates: ['2019-09-10', '2019-09-20'],
      isDatePicker: false,
    }
  },
  computed: {
    dateRangeText () {
      return this.dates.join(' ~ ')
    },
  },
}
</script>

<style scoped>
.date-modal {
  width: 300px;
  padding: 8px;
  background: #ffffff;
  border-radius: 4px;
  position: absolute;
  display: inline-block;
  max-width: 80%;
  contain: content;
  will-change: transform;
  box-shadow: 0px 5px 5px -3px rgba(0, 0, 0, 0.2), 0px 8px 10px 1px rgba(0, 0, 0, 0.14), 0px 3px 14px 2px rgba(0, 0, 0, 0.12);
  border-radius: 4px;
  z-index: 10000;
  
}
</style>