<template>
    <!-- 글목록 -->
    <v-row :style="{marginTop: '5%'}">
        <v-col cols="12" md="2" />
        <v-col cols="12" md="8">
            <v-row :style="{marginTop: '1%'}">
                <v-col cols="12" md="4" align="left">
                    <h1>{{ boardtitle }}</h1>
                    <hr />
                </v-col>
                <v-col cols="12" md="4" />
                <v-col v-if="bookMarkList == {}" cols="12" md="4" align="right" @click="moveTo('/community?page=1&category=all')"> 더보기 </v-col>
            </v-row>
            <v-row :style="{marginTop: '1%'}">
                <v-simple-table style="width: 100%">
                    <thead>
                        <tr style="font-weight: bolder">
                            <td style="width: 10%; font-size: large">No</td>
                            <td style="width: 50%; font-size: large">제목</td>
                            <td style="width: 15%; font-size: large">글쓴이</td>
                            <td style="width: 15%; font-size: large">작성시간</td>
                            <td style="width: 10%; font-size: large">좋아요</td>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(value, index) in bookMarkList" :key="index" @click="movetocontent(value.sequence)">
                            <td style="width: 10%; font-size: large">No</td>
                            <td style="width: 50%; font-size: large">{{ value.title }}</td>
                            <td style="width: 15%; font-size: large">{{ value.nickname }}</td>
                            <td style="width: 15%; font-size: large">{{ value.createdTime.split('T')[0] }}</td>
                            <td style="width: 10%; font-size: large">{{ value.loveCount }}</td>

                            <!-- Sequelize의 createdAt, updatedAt의 날짜 형식이 '2021-12-10T12:38:52.000Z' 이런 식이여서 
                               split('T')[0]을 통해 날짜만 표시 -->
                        </tr>
                    </tbody>
                </v-simple-table>
            </v-row>
        </v-col>
        <v-col cols="12" md="2" />
    </v-row>
</template>
<script>
export default {
    name: 'BoardList',
    props: {
        bookMarkList: {},
        boardtitle: {
            type: String
        }
    },
    methods: {
        async moveTo(link) {
            window.location.href = link;
        },
        movetocontent(index) {
            // 클릭된 글의 id를 받아와야 라우팅할때 보낼 수 있음
            this.$router.push({
                // path: window.location.pathname + '/content/' + id,
                name: 'CommuContent',
                params: {
                    id: index
                }
            });
            // window.location.href = window.location.pathname + '/content/' + id;
        }
    }
};
</script>
