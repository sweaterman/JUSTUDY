<template>
    <v-container>
        <div class="card_section">
            <!-- 대화기록 -->
            <v-row>
                <v-col>
                    <div id="chatBox" :style="{padding: '2%'}">
                        <div class="card_section_chat" v-for="(item, index) in getChatData" :key="index">
                            <v-row>
                                <v-col :style="{marginLeft: '5%'}">
                                    <h4>
                                        <span v-text="getChatData[index].name"></span>
                                    </h4>
                                </v-col>
                            </v-row>
                            <v-row :style="{marginTop: '-3%'}">
                                <v-col :style="{marginLeft: '5%'}">
                                    <span v-text="getChatData[index].message"></span>
                                </v-col>
                            </v-row>
                        </div>
                    </div>
                </v-col>
            </v-row>
        </div>

        <div class="card_section" :style="{marginTop: '4%'}">
            <!-- 대화 입력 창  -->
            <v-row :style="{padding: '2%'}" justify="center" align="center">
                <v-col cols="12" md="8">
                    <v-text-field type="text" @keyup.enter="transferWord" v-model="chatText" color="success" loading></v-text-field>
                    <!-- <input    /> -->
                </v-col>
                <v-col cols="12" md="4">
                    <v-btn @click="transferWord">
                        <span class="material-icons-outlined"> send </span>
                    </v-btn>
                </v-col>
            </v-row>
        </div>
    </v-container>
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
.card_section {
    padding: 10px;
    /* margin-bottom: 1px; */
    border-style: solid;
    border-color: #eeeeee;
    border-radius: 30px;
    /* border-width: thin; */
}
.card_section_chat {
    padding: 6px;
    /* margin-bottom: 1px; */
    border-style: solid;
    border-color: #f5f5f5;
    border-radius: 30px;
    /* border-width: thin; */
}
#chatBox {
    overflow-y: auto;
    height: 720px;
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
