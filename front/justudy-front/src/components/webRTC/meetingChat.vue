<template>
    <div>
        <!-- 종료 -->
        <div><v-btn @click="chatClose">X</v-btn></div>
        <!-- 대화기록 -->
        <div id="chatBox" style="">
            <div v-for="(item, index) in getChatData" :key="index">
                <span class="name" v-text="getChatData[index].name"></span>
                <span class="message" v-text="getChatData[index].message"></span>
            </div>
        </div>
        <!-- 대화 입력 창  -->
        <div><input type="text" @keyup.enter="transferWord" v-model="chatText" /><v-btn @click="transferWord">전송</v-btn></div>
    </div>
</template>

<script>
import {mapActions, mapGetters} from 'vuex';
const moduleWebRTC = 'moduleWebRTC';

export default {
    name: 'meetingChat',
    data() {
        return {
            chatText: ''
        };
    },
    computed: {
        ...mapGetters(moduleWebRTC, ['getChatData'])
    },
    created() {},
    methods: {
        ...mapActions(moduleWebRTC, ['setIsChat', 'transferChatMessage']),
        transferWord() {
            this.transferChatMessage(this.chatText);
            this.chatText = '';
        },
        chatClose() {
            this.setIsChat(false);
        }
    }
};
</script>

<style scoped>
#chatBox {
    overflow-y: auto;
    height: 90%;
}

input {
    background-color: darkgray;
    border: 2 solid black;
}
.name {
    background-color: gold;
}

.message {
    background-color: lightgray;
}
</style>
