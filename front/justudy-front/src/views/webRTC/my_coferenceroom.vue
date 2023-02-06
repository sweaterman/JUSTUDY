<template>
  <v-app>
    <v-row :style="{ marginTop: '5%' }">
      <v-col cols="12" md="1" />
      <v-col cols="12" md="8">
        <!-- 중앙 메인화면 -->
        <v-row>
          <div style="width: 100%" ref="main"></div>
        </v-row>
        <!-- 모든 참가자 화면 -->
        <v-row :style="{ marginTop: '10%' }">
          <!-- <v-sheet class="mx-auto" max-width="100%"> -->
          <!-- <v-sheet max-width="100%">
            <v-slide-group multiple show-arrows>
              <div
                style="display: inline-block"
                ref="el1"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el2"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el3"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el4"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el5"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el6"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el7"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el8"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el9"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el10"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el11"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el12"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el13"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el14"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el15"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el16"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el17"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el18"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el19"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el20"
                @click="viewChange"
              ></div>
            </v-slide-group>
          </v-sheet> -->
        </v-row>
      </v-col>

      <!-- 기능모음 -->
      <v-col cols="12" md="2" :style="{ marginLeft: '2%' }">
        <!-- 임시 -->
        <v-row
          style="
             {
              height: 100%;
            }
          "
        >
          <v-sheet>
            <v-slide-group multiple show-arrows>
              <div
                style="display: inline-block"
                ref="el1"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el2"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el3"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el4"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el5"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el6"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el7"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el8"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el9"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el10"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el11"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el12"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el13"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el14"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el15"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el16"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el17"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el18"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el19"
                @click="viewChange"
              ></div>
              <div
                style="display: inline-block"
                ref="el20"
                @click="viewChange"
              ></div>
            </v-slide-group>
          </v-sheet>
        </v-row>

        <!-- 임시 -->
        <v-row :style="{ padding: '1%' }">
          <!-- <div id="room" style="width: 100%"> -->
          <!-- 방 그냥 나가기 -->
          <v-btn type="button" id="button-leave" @mouseup="leaveRoom"
            >Leave room</v-btn
          >
        </v-row>
        <!-- 내 소리 온 오프 전환 -->
        <v-row :style="{ padding: '1%' }">
          <v-btn @click="setMute">소리on/off</v-btn>
        </v-row>

        <!-- 소리 상태 콘솔에 읽어보기 -->
        <v-row :style="{ padding: '1%' }">
          <v-btn @click="test">get소리</v-btn>
        </v-row>
        <!-- 내 화면 온 오프 전환 -->
        <v-row :style="{ padding: '1%' }">
          <v-btn @click="setScreen">화면on/off</v-btn>
        </v-row>
        <!-- 화면 상태 콘솔에 읽어보기 -->
        <v-row :style="{ padding: '1%' }">
          <v-btn @click="test2">get화면</v-btn>
        </v-row>
        <!-- 화면 공유 세팅 -->
        <v-row :style="{ padding: '1%' }">
          <v-btn @click="shareScreen">화면공유on/off</v-btn>
        </v-row>
        <!-- 참가자 이름을 불러옴.... -->
        <v-row :style="{ padding: '1%' }">
          <select v-model="selected" @click="getPeople" style="width: 100px">
            <option v-for="(item, index) in namesFromParticipants" :key="index">
              {{ item }}
            </option>
          </select>
        </v-row>
        <!-- 해당 인원 강퇴! 이건 방장기능이라 제안하지 않음 -->
        <v-row :style="{ padding: '1%' }">
          <v-btn @click="banParticipant">강퇴</v-btn>
        </v-row>
        <!-- 해당 인원 mute 제안하기 -->
        <v-row :style="{ padding: '1%' }">
          <v-btn @click="requestMute" v-bind:disabled="tempMuteBtnDisable"
            >mute제안</v-btn
          >
        </v-row>
        <!-- 모두다 나가기 제안하기 -->
        <v-row :style="{ padding: '1%' }">
          <v-btn @click="requestExit" v-bind:disabled="tempExitBtnDisable"
            >스터디 모두 종료 제안</v-btn
          >
        </v-row>
        <!-- 사다리타기 생성 -->
        <v-row :style="{ padding: '1%' }">
          <v-btn @click="onLadder">사다리타기</v-btn>
        </v-row>
        <!-- 채팅창 생성 -->
        <v-row :style="{ padding: '1%' }">
          <v-btn @click="onChat">채팅창</v-btn>
          <span v-show="getIsNewChat">‼</span>
          <v-btn @click="showEditor">에디터</v-btn>
        </v-row>
        <!-- </div> -->

        <!-- 우상단 알림 css 수정시에는 v-show를 true로 해주신 다음에 디자인 수정하시고, 원래대로 하시면 되겠습니다.-->
        <v-row :style="{ padding: '1%' }">
          <div class="alarm" v-show="getIsViewAlarmDiv">
            <v-btn @click="offViewAlarmDiv">X</v-btn>
            <h2 v-text="getAlarmDivText"></h2></div
        ></v-row>
        <!-- 우상단 Mute투표 -->
        <v-row :style="{ padding: '1%' }">
          <div class="alarm" v-show="getIsViewMuteDiv">
            <h2 v-text="getMuteDivText"></h2>
            <h3 v-text="getRemainTime"></h3>
            <v-btn @click="muteVote(true)">Yes</v-btn
            ><v-btn @click="muteVote(false)">No</v-btn>
          </div>
        </v-row>
        <!-- 우상단 방나가기투표 -->
        <v-row :style="{ padding: '1%' }">
          <div class="alarm" v-show="getIsViewExitDiv">
            <h2 v-text="getExitDivText"></h2>
            >
            <h3 v-text="getRemainTime"></h3>
            <v-btn @click="exitVote(true)">Yes</v-btn
            ><v-btn @click="exitVote(false)">No</v-btn>
          </div>
        </v-row>

        <!-- 사다리 타기(중앙에 배치) -->
        <ladder id="ladderCSS" v-if="getIsLadder" />
        <!--  채팅창 우측에 배치 -->
        <coferencechat id="chatCSS" v-show="getIsChat" />
        <!-- code editor  -->
        <CodeEditor
          class="leftArrange"
          height="800px"
          v-show="isShowEditor"
          v-model="codeContent"
          :language_selector="true"
          :languages="[
            ['javascript', 'JS'],
            ['python', 'Python'],
            ['cpp', 'c++'],
            ['java', 'Java'],
          ]"
        ></CodeEditor>
      </v-col>
      <v-col cols="12" md="1" />
    </v-row>
  </v-app>
</template>
<script>
import ladder from "@/views/ladder.vue";
import coferencechat from "@/views/coferencechat.vue";
import { mapActions, mapGetters } from "vuex";
import CodeEditor from "simple-code-editor";
const modulegroupcall = "modulegroupcall";

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
    ladder,
    coferencechat,
    CodeEditor,
  },
  data() {
    return {
      isShowEditor: false,
      namesFromParticipants: [], //이름만
      selected: "",
      tempMuteBtnDisable: false,
      tempExitBtnDisable: false,
    };
  },
  created() {
    this.setPersonName(localStorage.getItem("studyRoomPersonName")); //실제로 회원 닉네임을 전달해주면 됨.
    this.setRoomName(localStorage.getItem("studyRoomRoomName")); //해당 스터디방 seq값을 전달해주면 됨.
    this.setCodeEditor(this.codeContent);
  },
  beforeDestroy() {
    this.leave();
  },
  mounted() {
    // 최대 참가 수 만큼 엘리먼트 정보 넣어줌. (현재 20명).  아래 addInitEl 부분은 필수
    window.addEventListener("beforeunload", this.leaveRoom);
    this.addInitEl([
      this.$refs.el1,
      this.$refs.el2,
      this.$refs.el3,
      this.$refs.el4,
      this.$refs.el5,
      this.$refs.el6,
      this.$refs.el7,
      this.$refs.el8,
      this.$refs.el9,
      this.$refs.el10,
      this.$refs.el11,
      this.$refs.el12,
      this.$refs.el13,
      this.$refs.el14,
      this.$refs.el15,
      this.$refs.el16,
      this.$refs.el17,
      this.$refs.el18,
      this.$refs.el19,
      this.$refs.el20,
    ]);

    this.setMainParents(this.$refs.main);
    this.setSource("webcam");
    const data = {
      url: "ws://localhost:8080/groupcall",
      person: this.getPersonName,
      room: this.getRoomName,
    };
    this.open(data);
  },

  beforeUnmount() {
    window.removeEventListener("beforeunload", this.leaveRoom);
  },

  computed: {
    ...mapGetters(modulegroupcall, [
      "getPersonName",
      "getRoomName",
      "getAudio",
      "getScreen",
      "getParticipants",
      "getSource",
      "getIsViewAlarmDiv",
      "getIsViewMuteDiv",
      "getIsViewExitDiv",
      "getAlarmDivText",
      "getMuteDivText",
      "getExitDivText",
      "getRemainTime",
      "getIsLadder",
      "getIsChat",
      "getIsNewChat",
      "getCodeEditor",
    ]),
    codeContent: {
      get() {
        return this.getCodeEditor;
      },
      set: debounce(function (newValue) {
        this.sendCode(newValue);
      }, 400),
    },
  },
  methods: {
    ...mapActions(modulegroupcall, [
      "addInitEl",
      "setMainParents",
      "setSource",
      "open",
      "leave",
      "isSetAudio",
      "isSetScreen",
      "ban",
      "mute",
      "exit",
      "reset",
      "share",
      "requestMuteSend",
      "requestExitSend",
      "setViewAlarmDiv",
      "requestMuteSend",
      "setIsViewMuteDiv",
      "setIsViewExitDiv",
      "setIsLadder",
      "setIsChat",
      "setIsNewChat",
      "setPersonName",
      "setRoomName",
      "setCodeEditor",
      "sendCode",
    ]),
    showEditor() {
      if (this.isShowEditor) this.isShowEditor = false;
      else this.isShowEditor = true;
    },
    leaveRoom() {
      this.leave();
    },
    shareScreen() {
      const data = {
        url: "ws://localhost:8080/groupcall",
        person: this.getPersonName,
        room: this.getRoomName,
      };
      if (this.getSource == "desktop") {
        //반대로 가야 함.
        this.reset(data);
      } else {
        this.share(data);
      }
    },
    setMute() {
      this.isSetAudio(!this.getAudio);
    },
    setScreen() {
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
        if (value.name != this.getPersonName)
          this.namesFromParticipants.push(value.name);
      }
      console.log(this.namesFromParticipants);
    },
    banParticipant() {
      console.log(this.selected);
      this.ban(this.selected);
    },
    keepQuiet() {
      console.log(this.selected);
      this.mute(this.selected);
    },
    viewChange(el) {
      const curEl = el.currentTarget.firstChild;
      const mainView = this.$refs.main;
      while (mainView.hasChildNodes()) {
        mainView.removeChild(mainView.firstChild);
      }

      let canvas = document.createElement("canvas");
      let context = canvas.getContext("2d");
      let videoEl = curEl;

      canvas.style = "width:100%";
      mainView.appendChild(canvas);

      function updateCanvas() {
        context.drawImage(videoEl, 0, 0, canvas.width, canvas.height);

        window.requestAnimationFrame(updateCanvas);
      }

      requestAnimationFrame(updateCanvas);
    },
    offViewAlarmDiv() {
      this.setViewAlarmDiv(false);
    },
    requestMute() {
      if (this.selected == "") {
        console.log("음소거할 사람을 선택해주세요");
        return;
      }
      this.tempMuteBtnDisable = true;
      this.requestMuteSend(this.selected);
      setTimeout(() => {
        this.tempMuteBtnDisable = false;
      }, 6500); //여러번 누르기를 방지하기 위함(1초의 초기화 시간 + 5초의 투표시간)
    },
    requestExit() {
      this.tempExitBtnDisable = true;
      this.requestExitSend();
      setTimeout(() => {
        this.tempExitBtnDisable = false;
      }, 6500); //여러번 누르기를 방지하기 위함(1초의 초기화 시간 + 5초의 투표시간)
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
    //////////////////////////////////////////////////////
    test() {
      console.log(this.getAudio);
    },
    test2() {
      console.log(this.getScreen);
    },
  },
};
</script>

<style scoped>
/* 우상단 알람,방나가기,뮤트투표 기능 여기서 디자인하면 됨 */
.alarm {
  width: 37%;
  margin-left: 60%;
  background-color: aqua;
  position: absolute;
  top: 30px;
  height: 200px;
  z-index: 2;
}

.leftArrange {
  width: 37%;
  margin-left: 5%;
  margin-right: 58%;
  position: absolute;
  top: 30px;
  height: 800px;
  z-index: 2;
}

#ladderCSS {
  margin-left: 33%;
  width: 33%;
  position: absolute;
  z-index: 3;
  top: 300px;
  height: 500px;
  background-color: beige;
}

#chatCSS {
  margin-left: 66%;
  width: 33%;
  position: absolute;
  z-index: 1;
  top: 30px;
  height: 800px;
  background-color: darksalmon;
}

select {
  background-color: blanchedalmond;
}
</style>
