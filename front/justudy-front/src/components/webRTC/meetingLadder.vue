<template>
    <v-app>
        <v-row :style="{marginTop: '3%'}">
            <v-col cols="12" md="1">
                <v-btn @click="ladderClose">X</v-btn>
            </v-col>
            <v-col cols="12" md="10">
                <v-row>
                    <v-col cols="12" md="12" justify="center" align="center">
                        <div :style="{fontWeight: 'bold', fontSize: 'xx-large'}">제비 뽑기</div>
                    </v-col>
                </v-row>

                <!-- 참가자들 -->
                <v-row :style="{marginTop: '5%'}">
                    <v-row>
                        <div :style="{fontWeight: 'bold', fontSize: 'x-large'}">참여자</div>
                    </v-row>
                    <v-row justify="center" align="center">
                        <div>
                            <div v-for="(item, index) in namesFromParticipants" :key="index" v-text="item"></div>
                        </div>
                    </v-row>
                </v-row>

                <!-- 사다리타기 결과 적기 -->
                <v-row :style="{marginTop: '10%'}">
                    <v-row>
                        <div :style="{fontWeight: 'bold', fontSize: 'x-large'}">당첨 항목</div>
                    </v-row>
                    <v-row>
                        <div>
                            <div v-for="(item, index) in valuesFromParticipants" :key="index">
                                <label :for="index" v-text="index"></label>
                                <input :id="index" v-model="valuesFromParticipants[index]" />
                            </div>
                        </div>
                    </v-row>
                </v-row>

                <!-- 실행 버튼 -->
                <v-row :style="{marginTop: '25%'}">
                    <v-col cols="12" md="2">
                        <v-btn @click="ladderStart">사다리 돌리기</v-btn>
                    </v-col>
                    <v-col cols="12" md="8" />
                    <v-col cols="12" md="2">
                        <v-btn @click="ladderTransfer">결과 전송하기</v-btn>
                    </v-col>
                </v-row>
            </v-col>
            <v-col cols="12" md="1" />
        </v-row>
    </v-app>
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
            console.log(this.ladderResult);
        },
        ladderTransfer() {
            if (this.ladderResult.length < 1) {
                console.log('사다리 돌리기를 먼저해주세요');
                return;
            }
            this.sendLadderResult(this.ladderResult);
        }
    }
};
</script>

<style scoped>
input {
    background-color: darkgray;
    border: 2 solid black;
}
</style>
