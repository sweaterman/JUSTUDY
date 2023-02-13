<template>
    <div class="card_section">
        <v-container>
            <v-row>
                <v-col>
                    <v-row justify="center" align="center">
                        <v-col justify="center" align="center">
                            <div :style="{fontWeight: 'bold', fontSize: 'xx-large'}">제비 뽑기</div>
                        </v-col>
                    </v-row>

                    <!-- 참가자들 -->
                    <v-col :style="{padding: '12%'}">
                        <v-row>
                            <v-col>
                                <div :style="{fontWeight: 'bold', fontSize: 'x-large'}">참여자</div>
                                <hr :style="{width: '80%'}" />
                            </v-col>
                        </v-row>
                        <v-row justify="center" align="center">
                            <v-col>
                                <div>
                                    <div v-for="(item, index) in namesFromParticipants" :key="index" v-text="item"></div>
                                </div>
                            </v-col>
                        </v-row>
                    </v-col>

                    <!-- 사다리타기 결과 적기 -->
                    <v-col :style="{padding: '12%'}">
                        <v-row>
                            <v-col>
                                <div :style="{fontWeight: 'bold', fontSize: 'x-large'}">당첨 항목</div>
                                <hr :style="{width: '80%'}" />
                            </v-col>
                        </v-row>
                        <!-- 사다리타기 결과 적기 -->
                        <v-row>
                            <v-col>
                                <div v-for="(item, index) in valuesFromParticipants" :key="index">
                                    <v-row>
                                        <v-col cols="12" md="3" justify="center" align="center">
                                            <label :for="index" v-text="index + 1"></label>
                                        </v-col>
                                        <v-col cols="12" md="9" align="left">
                                            <input :id="index" v-model="valuesFromParticipants[index]" :style="{width: '100%'}" />
                                        </v-col>
                                    </v-row>
                                </div>
                            </v-col>
                        </v-row>
                    </v-col>

                    <!-- 실행 버튼 -->
                    <v-row :style="{padding: '12%'}">
                        <v-btn @click="ladderStart" color="white" :style="{color: 'green'}">
                            <v-col cols="12" md="4">
                                <span class="material-icons-outlined"> add_task </span>
                            </v-col>
                            <v-col cols="12" md="8">
                                <div>결과 확인</div>
                            </v-col>
                        </v-btn>
                    </v-row>
                </v-col>
            </v-row>
        </v-container>
    </div>
</template>

<script>
import {mapActions, mapGetters} from 'vuex';
const moduleWebRTC = 'moduleWebRTC';

export default {
    name: 'meetingLadder',
    data() {
        return {
            namesFromParticipants: [],
            valuesFromParticipants: [],
            ladderResult: []
        };
    },
    computed: {
        ...mapGetters(moduleWebRTC, ['getParticipants'])
    },
    created() {
        this.getPeople();
    },
    methods: {
        ...mapActions(moduleWebRTC, ['setIsLadder', 'sendLadderResult']),
        getPeople() {
            const obj = this.getParticipants;
            const keys = Object.keys(obj);
            this.namesFromParticipants = [];
            this.valuesFromParticipants = [];
            for (let i = 0; i < keys.length; i++) {
                const key = keys[i]; // 각각의 키
                const value = obj[key]; // 각각의 키에 해당하는 각각의 값
                this.namesFromParticipants.push(value.name);
                this.valuesFromParticipants.push('');
            }
        },
        ladderClose() {
            this.setIsLadder(false);
        },
        ladderStart() {
            let tempParticipants = this.namesFromParticipants.slice();
            let tempValues = this.valuesFromParticipants.slice();
            this.ladderResult = [];
            while (tempParticipants.length > 0) {
                let indexParticipant = Math.random() * tempParticipants.length;
                let indexValue = Math.random() * tempValues.length;
                let data = {
                    name: tempParticipants.splice(indexParticipant, 1)[0],
                    value: tempValues.splice(indexValue, 1)[0]
                };
                // console.log(data);
                this.ladderResult.push(data);
            }
            this.sendLadderResult(this.ladderResult);
            console.log(this.ladderResult);
        }
        // ladderTransfer() {
        //     if (this.ladderResult.length < 1) {
        //         console.log('사다리 돌리기를 먼저해주세요');
        //         return;
        //     }
        //     this.sendLadderResult(this.ladderResult);
        // }
    }
};
</script>

<style scoped>
.card_section {
    padding: 10px;
    /* margin-bottom: 1px; */
    border-style: solid;
    border-color: #eeeeee;
    border-radius: 30px;
    /* border-width: thin; */
}
</style>
<!-- <style scoped>
input {
    background-color: darkgray;
    border: 2 solid black;
}
</style> -->
