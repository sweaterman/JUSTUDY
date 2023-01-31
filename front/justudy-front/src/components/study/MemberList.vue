<template>
    <v-app>
        <v-container>
            <v-row>
                <v-col cols="12" md="2">
                    <v-row>
                        <v-col cols="12" align="center">
                            <h3 class="yellowText">멤버</h3>
                        </v-col>
                        <v-col cols="12" align="center">
                            <h3>{{ memberList.length }} / {{ population }}</h3>
                        </v-col>
                    </v-row>
                </v-col>
                <v-col cols="12" md="1" v-for="member in memberList" :key="member.sequence">
                    <v-avatar size="80">
                        <img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John" />
                    </v-avatar>
                    <div>
                        {{ member.nickname }}
                    </div>
                </v-col>
            </v-row>
            <v-row>
                <v-divider></v-divider>
            </v-row>
        </v-container>
    </v-app>
</template>

<script>
export default {
    name: 'MemberList',
    props: ['memberList', 'population']
};
</script>

<style scoped>
.yellowText {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 80px;
    height: 40px;
    background-color: #ffd200;
    border-radius: 30px;
}
</style>
