<template>
    <v-app>
        <v-row :style="{marginTop: '3%'}">
            <v-col cols="12" md="1" />
            <v-col cols="12" md="8">
                <!-- 모든 참가자 화면 -->
                <div style="width: 100%" ref="main"></div>
                <!-- code editor  -->
                <CodeEditor
                    width="100%"
                    height="720px"
                    v-show="isShowEditor"
                    v-model="codeContent"
                    justify-content="center"
                    align-items="center"
                    :language_selector="true"
                    :languages="[
                        ['javascript', 'JS'],
                        ['python', 'Python'],
                        ['cpp', 'c++'],
                        ['java', 'Java']
                    ]"
                ></CodeEditor>
            </v-col>
            <v-col cols="12" md="3" :style="{padding: '4%'}">
                <v-row> </v-row>
                <v-row>
                    <!-- 사다리 타기(중앙에 배치) -->
                    <!-- <meetingLadder id="ladderCSS" v-show="isLadder" v-if="getIsLadder" /> -->
                    <meetingLadder class="ladderCSS" v-show="isLadder" v-if="getIsLadder" />
                    <!--  채팅창 우측에 배치 -->
                    <meetingChat class="chatCSS" v-show="isChat" justify-content="center" align-items="center" />
                    <!-- 우상단 알림 css 수정시에는 v-show를 true로 해주신 다음에 디자인 수정하시고, 원래대로 하시면 되겠습니다.-->
                    <div class="card_alarm" v-show="getIsViewAlarmDiv">
                        <v-row>
                            <v-col>
                                <v-btn @click="offViewAlarmDiv" depressed color="white">X</v-btn>
                            </v-col>
                        </v-row>
                        <v-row justify="center" align="center">
                            <v-col justify="center" align="center">
                                <h2 v-text="getAlarmDivText"></h2>
                            </v-col>
                        </v-row>
                    </div>

                    <!-- 우상단 Ban투표 -->
                    <div class="card_alarm" v-show="getIsViewBanDiv">
                        <v-row>
                            <v-col>
                                <h2 v-text="getBanDivText"></h2>
                                <h3 v-text="getRemainTime"></h3>
                            </v-col>
                        </v-row>
                        <v-row justify="center" align="center">
                            <v-col justify="center" align="center">
                                <v-btn @click="banVote(true)" depressed color="white">Yes</v-btn><v-btn @click="banVote(false)" depressed color="white">No</v-btn>
                            </v-col>
                        </v-row>
                    </div>

                    <!-- 우상단 Mute투표 -->
                    <div class="card_alarm" v-show="getIsViewMuteDiv">
                        <v-row>
                            <v-col>
                                <h2 v-text="getMuteDivText"></h2>
                            </v-col>
                        </v-row>
                        <v-row justify="center" align="center">
                            <v-col justify="center" align="center">
                                <h3 v-text="getRemainTime"></h3>
                                <v-btn @click="muteVote(true)" depressed color="white">Yes</v-btn><v-btn @click="muteVote(false)" depressed color="white">No</v-btn>
                            </v-col>
                        </v-row>
                    </div>

                    <!-- 우상단 방나가기투표 -->
                    <div class="card_alarm" v-show="getIsViewExitDiv">
                        <v-row>
                            <v-col>
                                <h2 v-text="getExitDivText"></h2>
                            </v-col>
                        </v-row>
                        <v-row justify="center" align="center">
                            <v-col justify="center" align="center">
                                <h3 v-text="getRemainTime"></h3>
                                <v-btn @click="exitVote(true)" depressed color="white">Yes</v-btn><v-btn @click="exitVote(false)" depressed color="white">No</v-btn>
                            </v-col>
                        </v-row>
                    </div>
                </v-row>
            </v-col>
        </v-row>

        <!-- 기능모음 -->
        <v-row :style="{marginTop: '3%', marginBottom: '2%'}">
            <v-col cols="12" md="1" />
            <v-col cols="12" md="10">
                <v-row>
                    <!-- 내 화면 기능 -->
                    <v-col cols="12" md="1" align="right">
                        <!-- 내 소리 온 오프 전환 -->
                        <v-btn v-if="soundon" @click="setMute" color="white" depressed>
                            <span class="material-icons-outlined"> volume_up </span>
                        </v-btn>
                        <v-btn v-if="!soundon" @click="setMute" color="white" depressed>
                            <span class="material-icons-outlined"> volume_off </span>
                        </v-btn>
                    </v-col>
                    <v-col cols="12" md="1" align="center">
                        <!-- 내 화면 온 오프 전환 -->
                        <v-btn v-if="cameraon" @click="setScreen" color="white" depressed>
                            <span class="material-icons-outlined"> visibility </span>
                        </v-btn>
                        <v-btn v-if="!cameraon" @click="setScreen" color="white" depressed>
                            <span class="material-icons-outlined"> visibility_off </span>
                        </v-btn>
                    </v-col>
                    <v-col cols="12" md="1" align="left" v-show="!isMobile">
                        <!-- 화면 공유 세팅 -->
                        <v-btn v-if="!shareon" @click="shareScreen" color="white" depressed>
                            <span class="material-icons-outlined"> airplay </span>
                        </v-btn>
                        <v-btn v-if="shareon" @click="shareScreen" color="white" depressed>
                            <span class="material-icons-outlined"> web_asset_off </span>
                        </v-btn>
                    </v-col>
                    <v-col cols="12" md="1" />

                    <!-- 투표 관련 기능 -->
                    <v-col cols="12" md="2" align="right">
                        <!-- 참가자 이름을 불러옴.... -->
                        <v-select v-model="selected" :items="namesFromParticipants" @click="getPeople" max-width="300">
                            <!-- <option v-for="(item, index) in namesFromParticipants" :key="index"> -->
                            <!-- </option> -->
                        </v-select>
                    </v-col>

                    <v-col cols="12" md="1" align="right">
                        <!-- 해당 인원 강퇴 제안하기-->
                        <v-btn @click="requestBan" v-bind:disabled="btnDisable" color="white" depressed>
                            <span class="material-icons-outlined" style="color: orange"> waving_hand </span>
                        </v-btn>
                    </v-col>

                    <v-col cols="12" md="1" align="center">
                        <!-- 해당 인원 mute 제안하기 -->
                        <v-btn @click="requestMute" v-bind:disabled="btnDisable" color="white" depressed>
                            <span class="material-icons-outlined" style="color: orange"> voice_over_off </span>
                        </v-btn>
                    </v-col>

                    <v-col cols="12" md="1" align="left">
                        <!-- 모두다 나가기 제안하기 -->
                        <v-btn @click="requestExit" v-bind:disabled="btnDisable" color="white" depressed>
                            <span class="material-icons-outlined" style="color: orange"> power_settings_new </span>
                        </v-btn>
                    </v-col>

                    <!-- 모달 생성 -->
                    <v-col cols="12" md="1" align="right">
                        <!-- 사다리타기 생성 -->
                        <v-btn @click="modalClicked('onLadder')" color="white" depressed>
                            <span class="material-icons-outlined"> rule </span>
                        </v-btn>
                    </v-col>

                    <v-col cols="12" md="1" align="center">
                        <!-- 채팅창 생성 -->
                        <v-btn @click="modalClicked('onChat')" color="white" depressed>
                            <span class="material-icons-outlined"> speaker_notes </span>
                        </v-btn>
                        <span v-show="getIsNewChat">‼</span>
                    </v-col>

                    <v-col cols="12" md="1" align="left">
                        <!-- 에디터 생성 -->
                        <v-btn @click="modalClicked('showEditor')" color="white" depressed>
                            <span class="material-icons-outlined"> integration_instructions </span>
                        </v-btn>
                    </v-col>
                </v-row>
            </v-col>
            <v-col cols="12" md="1">
                <!-- 방 그냥 나가기 -->
                <v-row :style="{marginTop: '2%'}">
                    <v-btn type="button" id="button-leave" @mouseup="leaveRoom" color="white" depressed>
                        <span class="material-icons-outlined" style="color: crimson"> logout </span>
                    </v-btn>
                </v-row>
            </v-col>
        </v-row>
    </v-app>
</template>
<script>
import meetingLadder from '../../components/webRTC/meetingLadder.vue';
import meetingChat from '../../components/webRTC/meetingChat.vue';
import {mapActions, mapGetters} from 'vuex';
import CodeEditor from 'simple-code-editor';
const moduleWebRTC = 'moduleWebRTC';
function debounce(fn, delay) {
    var timeoutID = null;
    return function () {
        clearTimeout(timeoutID);
        var args = arguments;
        var that = this;
        timeoutID = setTimeout(function () {
            fn.apply(that, args);
        }, delay);
    };
}
export default {
    components: {
        meetingLadder,
        meetingChat,
        CodeEditor
    },
    data() {
        return {
            // 도균
            soundon: true,
            cameraon: true,
            shareon: false,
            isLadder: false,
            isChat: false,

            //의석
            isShowEditor: false,
            namesFromParticipants: [], //이름만
            selected: '',
            btnDisable: false,
            isMobile: /Mobi/i.test(window.navigator.userAgent)
        };
    },
    created() {
        this.setPersonName(localStorage.getItem('studyRoomPersonName')); //실제로 회원 닉네임을 전달해주면 됨.
        this.setRoomName(localStorage.getItem('studyRoomRoomName')); //해당 스터디방 seq값을 전달해주면 됨.
        this.setCodeEditor(this.codeContent);
    },
    beforeDestroy() {
        this.leave();
    },
    mounted() {
        window.addEventListener('beforeunload', this.unLoadEvent);
        this.addInitEl(this.InitEl);
        this.setMainParents(this.$refs.main);
        this.setSource('webcam');
        const data = {
            url: 'wss://i8a104.p.ssafy.io/groupcall',
            // url: 'ws://localhost:8080/groupcall',
            person: this.getPersonName,
            room: this.getRoomName
        };
        this.open(data);
        setInterval(() => {
            const obj = this.getParticipants;
            const keys = Object.keys(obj);
            console.log('=========state============');
            for (let i = 0; i < keys.length; i++) {
                const key = keys[i];
                const value = obj[key];
                if ('connected' != value.rtcPeer.peerConnection.connectionState && 'connecting' != value.rtcPeer.peerConnection.connectionState ) {
                    
                    console.log(value.rtcPeer.peerConnection.connectionState);
                    location.reload();
                    // break;
                }
            }
            console.log('=========================');
        }, 3000);
    },

    beforeUnmount() {
        window.removeEventListener('beforeunload', this.unLoadEvent);
    },

    computed: {
        ...mapGetters(moduleWebRTC, [
            'getPersonName',
            'getRoomName',
            'getAudio',
            'getScreen',
            'getParticipants',
            'getSource',
            'getIsViewAlarmDiv',
            'getIsViewBanDiv',
            'getIsViewMuteDiv',
            'getIsViewExitDiv',
            'getAlarmDivText',
            'getBanDivText',
            'getMuteDivText',
            'getExitDivText',
            'getRemainTime',
            'getIsLadder',
            'getIsChat',
            'getIsNewChat',
            'getCodeEditor'
        ]),
        codeContent: {
            get() {
                return this.getCodeEditor;
            },
            set: debounce(function (newValue) {
                this.sendCode(newValue);
            }, 400)
        }
    },
    methods: {
        modalClicked(click) {
            if (click == 'onLadder') {
                this.isLadder = !this.isLadder;
                this.isChat = false;

                this.setIsLadder(true);
            } else if (click == 'onChat') {
                this.isLadder = false;
                this.isChat = !this.isChat;

                this.setIsChat(true);
                this.setIsNewChat(false);
            } else if (click == 'showEditor') {
                this.isShowEditor = !this.isShowEditor;
            }
        },
        ...mapActions(moduleWebRTC, [
            'addInitEl',
            'setMainParents',
            'setSource',
            'open',
            'leave',
            'isSetAudio',
            'isSetScreen',
            'ban',
            'mute',
            'exit',
            'reset',
            'share',
            'requestBanSend',
            'requestMuteSend',
            'requestExitSend',
            'setViewAlarmDiv',
            'setIsViewMuteDiv',
            'setIsViewBanDiv',
            'setIsViewExitDiv',
            'setIsLadder',
            'setIsChat',
            'setIsNewChat',
            'setPersonName',
            'setRoomName',
            'setCodeEditor',
            'sendCode'
        ]),

        // showEditor() {
        //     if (this.isShowEditor) this.isShowEditor = false;
        //     else this.isShowEditor = true;
        // },
        leaveRoom() {
            this.leave();
        },
        shareScreen() {
            this.shareon = !this.shareon;
            const data = {
                url: 'wss://i8a104.p.ssafy.io/groupcall',
                // url: 'ws://localhost:8080/groupcall',
                person: this.getPersonName,
                room: this.getRoomName
            };
            if (this.getSource == 'desktop') {
                //반대로 가야 함.
                this.reset(data);
            } else {
                this.share(data);
            }
        },
        setMute() {
            this.soundon = !this.soundon;
            this.isSetAudio(!this.getAudio);
        },
        setScreen() {
            this.cameraon = !this.cameraon;
            this.isSetScreen(!this.getScreen);
        },
        getPeople() {
            const obj = this.getParticipants;
            console.log(obj);
            const keys = Object.keys(obj); //
            this.namesFromParticipants = [];
            for (let i = 0; i < keys.length; i++) {
                const key = keys[i]; // 각각의 키
                const value = obj[key]; // 각각의 키에 해당하는 각각의 값
                if (value.name != this.getPersonName) this.namesFromParticipants.push(value.name);
            }
            console.log(this.namesFromParticipants);
        },
        keepQuiet() {
            this.mute(this.selected);
            this.selected = '';
        },
        offViewAlarmDiv() {
            this.setViewAlarmDiv(false);
        },
        requestBan() {
            if (this.selected == '') {
                alert('강제 퇴장을 할 사람을 선택해주세요');
                return;
            }
            this.btnDisable = true;
            // this.ban(this.selected);
            this.requestBanSend(this.selected);
            setTimeout(() => {
                this.btnDisable = false;
            }, 6500);
            this.selected = '';
        },
        requestMute() {
            if (this.selected == '') {
                alert('음소거할 사람을 선택해주세요');
                return;
            }
            this.btnDisable = true;
            this.requestMuteSend(this.selected);
            setTimeout(() => {
                this.btnDisable = false;
            }, 6500);
            this.selected = '';
        },
        requestExit() {
            this.btnDisable = true;
            this.requestExitSend();
            setTimeout(() => {
                this.btnDisable = false;
            }, 6500);
        },
        banVote(isSelected) {
            if (isSelected) {
                this.ban();
            }
            this.setIsViewBanDiv(false);
        },
        muteVote(isSelected) {
            if (isSelected) {
                this.mute();
            }
            this.setIsViewMuteDiv(false);
        },
        exitVote(isSelected) {
            if (isSelected) {
                this.exit();
            }
            this.setIsViewExitDiv(false);
        },
        onLadder() {
            this.setIsLadder(true);
        },
        onChat() {
            this.setIsChat(true);
            this.setIsNewChat(false);
        },
        unLoadEvent() {
            let start = new Date();
            let end = new Date();
            this.leaveRoom();
            while (end - start < 1000) {
                end = new Date();
            }
        }
    }
};
</script>

<style scoped>
/* 우상단 알람,방나가기,뮤트투표 기능 여기서 디자인하면 됨 */
.card_alarm {
    padding: 15px;
    width: 80%;
    height: 280px;
    margin-top: 20px;
    border-style: solid;
    border-color: #eeeeee;
    border-radius: 30px;
    /* border-width: thin; */
}
.alarm {
    width: 30%;

    background-color: rgb(132, 220, 230);
    position: absolute;
    top: 30px;
    height: 200px;
    z-index: 2;
}
.leftArrange {
    width: 37%;
    /* margin-left: 5%; */
    margin-right: 58%;
    position: absolute;
    top: 30px;
    height: 800px;
    z-index: 2;
}
#ladderCSS {
    margin-left: 55%;
    margin-top: 30%;
    width: 33%;
    display: table-row;
    z-index: 1;
    top: 30px;
    width: 100%;
    background-color: rgb(222, 222, 222);
}
#chatCSS {
    /* margin-right: 15%;
    margin-bottom: 25%;
    width: 33%;
    position: absolute;
    z-index: 3;
    top: 30px;
    height: 80px; */
    width: 100%;
    height: 800px;
}
select {
    background-color: blanchedalmond;
}
</style>
