<template>
  <div class="kanban MoveableBox" ref="kanban">
    <div class="flex justify-center">
      <div class="d-flex">

        <div
          v-for="column in this.$store.state.Kanban.states"
          :key="column.columnTitle"
          @click="kanbanClickEvent"
          class="kanban-column bg-gray-100 rounded-lg px-3 py-3 column-width rounded mr-4"
        >
          <p class="text-gray-700 font-semibold font-sans tracking-wide text-sm">{{column.columnTitle}}</p>
          
          <draggable
            :list="column.tasks"
            :animation="200"
            class="draggable-box"
            ghost-class="ghost-card"
            group="tasks"

          >
            <!-- Each element from here will be draggable and animated. Note :key is very important here to be unique both for draggable and animations to be smooth & consistent. -->

            <div v-for="(task,idx) in column.tasks"
             :key="idx" class="cursor-move mb-3">
              <div 
              @click="showTask(column.columnTitle, task)" 
              @dragend="$store.commit('toggleUpdate')"
              class="bg-white shadow rounded px-3 pt-3 pb-5 border border-white" 
              style="cursor: pointer;">
                <div class="kanban-task d-flex justify-space-between ">
                  <p
                    class="text-truncate text-gray-700 font-semibold font-sans text-sm word-break:keep-all;"
                    style="word-break:keep-all; "
                    
                  >
                  {{task.taskTitle}}
                  </p>
                 

                  <div>
                    <v-icon v-on:click="deleteTask(column.columnTitle,task)">mdi-delete</v-icon>
                  </div>
                </div>
              </div>
            </div>

          </draggable>
          <div v-on:click="addTask(column.columnTitle)" class="pressDownButton mt-3">+ add another card</div>
        </div>
      </div>
    </div>
    
    <v-dialog max-width="600px" persistent v-model="dialog">
      <v-card>
        <v-card-title>
          <h3>{{ this.newColumnTitle }} </h3>
        </v-card-title>
        <v-card-text>
          <v-form class="px-3" ref="form">
            <v-text-field
              label="제목"
              prepend-icon="mdi-subtitles"
              v-model="newTask.taskTitle"
            ></v-text-field>
            <v-textarea
              label="내용"
              prepend-icon="mdi-pencil"
              v-model="newTask.taskContents"
            ></v-textarea>
            <div class="text-center">
              <v-btn text class="primary white--text mx-2 mt-3" @click="submit" v-if="isAdd">추가</v-btn>
              <v-btn text class="primary white--text mx-2 mt-3" @click="close">닫기</v-btn>
            </div>
          </v-form>
        </v-card-text>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import draggable from "vuedraggable";

export default {
  name: "App",
  components: {
    draggable,
  },
  props:{kanban:Object}
  ,
  data() {
    return {
      task: {
        taskTitle:"",
        taskContents:"",
        taskAssigner:"",
      },
      states: [
        {
          columnTitle: "TO DO",
          tasks: [],
        },
        {
          columnTitle: "IN PROGRESS",
          tasks: [],
        },
        {
          columnTitle: "DONE",
          tasks: [],
        },
      ],
      dialog: false,
      isAdd: false,
      newTask: {
        taskTitle : "",
        taskContents : "",
        taskAssigner : "",
      },
      newColumnTitle: '',
    };
  },
  methods: {
    addTask(columnTitle) {
      this.isAdd = true
      this.dialog = true
      this.newColumnTitle = columnTitle;
    },
    deleteTask(columnTitle, task) {
      event.stopPropagation();
      var index = this.states
        .find((column) => column.columnTitle === columnTitle)
        .tasks.indexOf(task);

      // var index = tasks.indexOf(task);

      this.states
        .find((column) => column.columnTitle === columnTitle)
        .tasks.splice(index, 1);
      this.$store.state.Kanban.states
        .find((column) => column.columnTitle === columnTitle)
        .tasks.splice(index, 1);
      this.$store.commit('toggleUpdate');
    },
    showTask(columnTitle, task) {
       var index = this.states
        .find((column) => column.columnTitle === columnTitle)
        .tasks.indexOf(task);
        this.newColumnTitle = columnTitle
        this.newTask = task;
        this.isAdd = false; 
        this.dialog = true;
    },
    submit() {
      this.states.find((column) => column.columnTitle === this.newColumnTitle).tasks.push(this.newTask);
      this.$store.state.Kanban.states.find((column) => column.columnTitle === this.newColumnTitle).tasks.push(this.newTask);
      this.dialog = false;
      this.newTask = {
        taskTitle : "",
        taskContents : "",
        taskAssigner : "",
      }
      this.$store.commit('toggleUpdate');
    },
    close() {
      this.dialog = false; 
      this.newTask = {
        taskTitle : "",
        taskContents : "",
        taskAssigner : "",
      }
    },
    kanbanClickEvent({target}){
      console.log(target);
      target.focus();
    },
  },
};
</script>
<style src="../../assets/css/my-component.css"></style>
<style scoped>
.column-width {
  min-width: 250px;
  width: 250px;
  margin-right: 0;
}
/* Unfortunately @apply cannot be setup in codesandbox, 
but you'd use "@apply border opacity-50 border-blue-500 bg-gray-200" here */
.ghost-card {
  opacity: 0.5;
  background: #f7fafc;
  border: 1px solid #4299e1;
}
.kanban 
{
  /* background-color: #F5F5F5; */
  /* background-color: white; */
  
  padding: 15px;    
  /* box-shadow: .5rem 1rem 2rem rgba(0,0,0,.4)!important; */
  border-radius: 5px;
  /* width: 810px; */

}
.kanban-column {
  background-color: #F5F5F5;
  
  box-shadow: .5rem 1rem 2rem rgba(0,0,0,.4)!important;
}
.pressDownButton {
  cursor: pointer;
  background-color: #d6d6d6;
  color: rgb(68, 68, 68);
  border-bottom: 0px;
  transition: all 0.2s ease-in-out;
}

.pressDownButton:hover {
  background: rgba(0,0,0,0.4);
  color: white;
}

.draggable-box {
  margin-right: 0; 
  min-height: 50px;
  border: dashed 2px #d6d6d6;
}
</style>
