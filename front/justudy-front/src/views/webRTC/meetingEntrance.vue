<template>
    <v-app>
        <v-row :style="{marginTop: '5%'}">
            <v-col cols="12" md="1" />
            <v-col cols="12" md="7" justify="center" align="center">
                <v-img :src="require('@/assets/RTCenter.gif')" :style="`width: 1200; height: 1000; `" />
            </v-col>
            <v-col cols="12" md="3" align="left">
                <!-- <div class="home"> -->
                <!-- <div id="container"> -->
                <!-- <div id="wrapper">
          <div id="join" class="animate join"> -->
                <v-row>
                    <h1>오늘도 스터디 해볼까요?</h1>
                    <!-- <hr :style="{ width: '50%' }" /> -->
                </v-row>
                <v-row :style="{marginTop: '10%'}">
                    <v-col cols="12" md="4" justify="center" align="center">
                        <h1>ID</h1>
                    </v-col>
                    <v-col cols="12" md="8">
                        <v-text-field v-model="person" type="text" name="name" value="" id="name" required />
                    </v-col>
                </v-row>
                <v-row>
                    <v-col cols="12" md="4" justify="center" align="center">
                        <h1>Room</h1>
                    </v-col>
                    <v-col cols="12" md="8">
                        <v-text-field v-model="room" type="text" name="room" value="" id="roomName" required></v-text-field>
                    </v-col>
                </v-row>
                <v-row justify="center" align="center">
                    <v-col>
                        <v-btn elevation="2" x-large @click="register" name="commit">join</v-btn>
                    </v-col>
                </v-row>
                <!-- </div>
        </div> -->
                <!-- </div>
      </div> -->
            </v-col>
            <v-col cols="12" md="1" />
        </v-row>
    </v-app>
</template>

<script>
import {mapActions} from 'vuex';
const moduleWebRTC = 'moduleWebRTC';

export default {
    data() {
        return {
            person: '',
            room: '1'
        };
    },
    methods: {
        ...mapActions(moduleWebRTC, ['setPersonName', 'setRoomName']),
        register() {
            localStorage.setItem('studyRoomPersonName', this.person);
            localStorage.setItem('studyRoomRoomName', this.room);
            // this.$router.push({path: `/study/create`});
            this.$router.push('/meeting/room');
        }
    },
    created() {
        this.person = Math.random().toString(36).substring(2, 12);
    }
};
</script>
