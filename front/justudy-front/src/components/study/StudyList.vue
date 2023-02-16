<template>
    <v-container>
        <v-row>
            <v-col cols="12">
                <v-row>
                    <v-col cols="4" v-for="study in studies" :key="study.sequence">
                        <!-- 한 개의 스터디를 감싸고 있는 div -->
                        <div class="singleStudy">
                            <v-row justify="center">
                                <div v-if="type != 'apply'" style="display: flex; justify-content: center; align-items: center">
                                    <img class="hover" style="width: 80%" :src="`${port}images/${study.imageSequence}`" alt="study_image" @click="moveToStudy(study.sequence)" />
                                </div>

                                <div v-if="type == 'apply'" class="container" style="display: flex; justify-content: center; align-items: center">
                                    <img class="hover" style="width: 80%" :src="`${port}images/${study.imageSequence}`" alt="study_image" @click="moveToStudy(study.sequence)" />
                                    <v-btn rounded color="error" class="cancleApply" @click="deleteApply('open', study.sequence)">X</v-btn>
                                </div>
                            </v-row>
                            <v-row style="margin-bottom: 5px">
                                <v-col cols="12">
                                    <h4 class="hover" @click="moveToStudy(study.sequence)">{{ study.name }}</h4>
                                </v-col>
                            </v-row>

                            <v-row no-gutters align="center">
                                <v-col cols="5">
                                    <v-subheader style="height: fit-content; padding: 0px">모집 현황</v-subheader>
                                </v-col>
                                <v-col cols="7" v-if="checkPersonnel(study.member.length, study.population)" style="color: #ff0000; font-weight: bold; font-size: 14px">
                                    {{ study.member.length }} / {{ study.population }}
                                </v-col>
                                <v-col cols="7" v-if="!checkPersonnel(study.member.length, study.population)" style="color: #3edf23; font-weight: bold; font-size: 14px">
                                    {{ study.member.length }} / {{ study.population }}
                                </v-col>
                            </v-row>
                            <v-row no-gutters align="center">
                                <v-col cols="5">
                                    <v-subheader style="height: fit-content; padding: 0px">시작 예정일</v-subheader>
                                </v-col>
                                <v-col cols="7" style="font-size: 14px">
                                    {{ study.startTime }}
                                </v-col>
                            </v-row>
                            <v-row no-gutters align="center">
                                <v-col cols="5">
                                    <v-subheader style="height: fit-content; padding: 0px">스터디장</v-subheader>
                                </v-col>
                                <v-col cols="7" style="font-size: 14px">
                                    {{ study.leader }}
                                </v-col>
                            </v-row>
                            <v-row dense>
                                <v-chip class="chip" color="yellow lighten-4">{{ study.frequency.map(row => row.week).join() }}</v-chip>
                                <v-chip class="chip" color="yellow lighten-4">{{ study.meeting }}</v-chip>
                                <v-chip class="chip" color="yellow lighten-4">{{ study.level }}</v-chip>
                            </v-row>
                        </div>
                    </v-col>
                </v-row>
            </v-col>
        </v-row>

        <!-- 지원취소 모달 -->
        <v-dialog v-model="deleteModal" width="500">
            <v-card>
                <v-card-title class="text-h5 grey lighten-2"> 지원 취소 </v-card-title>
                <v-card-text> 지원을 취소하시면, 스터디장에게 간 메세지가 사라집니다. 정말로 취소하시겠습니까? </v-card-text>
                <v-divider></v-divider>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="primary" text @click="deleteApply('T', 0)"> 확인 </v-btn>
                    <v-btn color="primary" text @click="deleteApply('F', 0)"> 취소 </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-container>
</template>

<script>
import port from '@/store/port';

export default {
    name: 'StudyList',
    data() {
        return {
            tab: null,
            deleteModal: false,
            deleteNum: null,
            week: null,
            port: port
        };
    },
    props: ['studies', 'type'],
    methods: {
        checkPersonnel(c, p) {
            //초록색이면 false, 빨간색이면 true
            if (Number(p) - Number(c) > 2) {
                return false;
            } else {
                return true;
            }
        },
        moveToStudy(seq) {
            //홍보 중 스터디이거나 지원한 스터디이면?
            if (this.type == 'promotion' || this.type == 'apply') {
                this.$router.push({path: `/study/search/${seq}`});
            }
            //내가 가입한 스터디이면?
            else {
                this.$router.push({path: `/study/${seq}/info`});
            }
        },
        async deleteApply(click, seq) {
            if (click == 'open') {
                this.deleteModal = true;
                this.deleteNum = seq;
            } else if (click == 'T') {
                //deleteNum 을 서버에 보내기
                await this.$store.dispatch('moduleStudy/deleteApply', this.deleteNum);
                this.deleteModal = false;
                this.deleteNum = null;
            } else {
                this.deleteModal = false;
            }
        }
    }
};
</script>

<style scoped>
.chip {
    margin: 5px 5px 0px 0px;
}

.hover {
    cursor: pointer;
}

.singleStudy {
    padding: 20px;
    margin-bottom: 20px;
    border-style: solid;
    border-color: #eeeeee;
    border-radius: 30px;
    border-width: thin;
}

.container {
    position: relative;
}
.cancleApply {
    position: absolute;
    top: 5px;
    left: 5px;
}
</style>
