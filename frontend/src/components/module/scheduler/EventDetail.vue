<template>
    <v-row justify="center">
        <v-dialog v-model="dialog" persistent max-width="400">
            <v-card>
                <v-card-title class="headline">{{event.title}}</v-card-title>
                <v-card-text style="font-size: 1rem" class="font-weight-bold">{{event.content}}</v-card-text>

                <div class="ml-5 font-weight-light">
                    <div>시작일: {{getEventStart()}}</div>
                    <div>종료일: {{getEventEnd()}}</div>
                </div>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="green darken-1" text @click="close()">닫기</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-row>
</template>

<script>
    export default {
        name: "EventDetail",
        computed: {
            dialog() {
                return this.$store.state.scheduler.eventDetail;
            },
            event() {
                return this.$store.state.scheduler.event;
            }
        },
        methods: {
            getEventStart() {
                return this.event.startDate + getTime(this.event.startTime);
            },
            getEventEnd() {
                return this.event.endDate + getTime(this.event.endTime);
            },
            close(){
                return this.$store.commit('CLOSE_SCHEDULER_EVENT');
            }
        }
    }

    const getTime = (time) => {
        return time === null ? '' : ` ${time}`;
    };
</script>
