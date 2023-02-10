<template>
    <v-app>
        
        <v-row :style="{marginTop: '3%'}">
            <v-col cols="12" md="1" >
                <v-row>
                    <v-col cols="12" md="3" ></v-col>
                    <v-col cols="12" md="9" >
                        <TabIcon style="height:100px"></TabIcon>
                    </v-col>
                </v-row>
            </v-col>
            <v-col cols="12" md="11" >
                <!-- 여기에 내용쓰기 -->
                <v-row>
                    <v-col cols="12" md="8" :style="{fontSize: 'xx-large'}" align="center">{{ id }}</v-col>
                    <v-col cols="12" md="4"></v-col>
                </v-row>
                <v-row>
                    <v-col cols="12" md="1"></v-col>
                    <v-col cols="12" md="6" >
                        <hr />
                        <v-row>
                            <v-col cols="12" md="2">닉네임</v-col>
                            <v-col cols="12" md="10" align="center"></v-col>
                        </v-row>
                        <hr />
                        <v-row>
                            <v-col cols="12" md="2">학번</v-col>
                            <v-col cols="12" md="10" align="center"></v-col>
                        </v-row>
                        <hr />
                        <v-row>
                            <v-col cols="12" md="2">희망 진로</v-col>
                            <v-col cols="12" md="10" align="center"></v-col>
                        </v-row>
                        <hr />
                        <v-row>
                            <v-col cols="12" md="2">가입 시간</v-col>
                            <v-col cols="12" md="10" align="center"></v-col>
                        </v-row>
                        <hr />
                        <v-row>
                            <v-col cols="12" md="2">소개</v-col>
                            <v-col cols="12" md="10" align="center"></v-col>
                        </v-row>
                        <hr />
                    </v-col>
                    <v-col cols="12" md="1"></v-col>
                    <v-col cols="12" md="3" >
                        <!-- 이미지 넣기 -->
                        <img class="imgCSS" style="width:100%" :src="link"/>
                    </v-col>
                    <v-col cols="12" md="1"></v-col>
                </v-row>

            </v-col>
        </v-row>
        <v-row>
            <v-col cols="12" md="5"></v-col>
            <v-col cols="12" md="2"><v-btn color="yellow" style="width:100%" @click="updateUser">수정하기</v-btn></v-col>
            <v-col cols="12" md="2"><v-btn color="yellow" style="width:100%" @click="deleteUser">삭제하기</v-btn></v-col>
            <v-col cols="12" md="2"><v-btn color="yellow" style="width:100%" @click="moveToList">목록보기</v-btn></v-col>
            <v-col cols="12" md="1"></v-col>
        </v-row>
    </v-app>
</template>

<script>
import TabIcon from '@/components/Admin/TabIcon.vue';
export default {
    name: 'AdminUserContent',
    components: {TabIcon},

    data() {
        return {
            id:this.$route.params.id,
            link:"https://images.unsplash.com/photo-1611825715408-826f2b19c43f?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=200&ixlib=rb-1.2.1&q=80&w=200",
        };
    },

    methods: {
        updateUser(){

        },
        deleteUser(){

        },
        moveToList(){
            window.location.href="/admin/user"
        },
    }
};
</script>

<style scoped>
.imgCSS {
    margin: 2%;
    border-radius: 25%;
}

</style>