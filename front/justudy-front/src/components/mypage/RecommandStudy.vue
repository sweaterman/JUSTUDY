<template>
    <v-row :style="{marginLeft: '2%'}">
        <v-row :style="{marginBottom: '2%'}">
            <v-btn color="yellow" @click="movetomain" :style="{height: '50px', width: '170px', fontWeight: 'bold', fontSize: 'x-large'}">추천 스터디</v-btn>
        </v-row>
        <v-row>
            <div class="d-flex mr-1" v-for="item in 3" v-bind:key="item">
                <div>
                    <img src="../../assets/logo.jpg" width="200px" height="200px" style="padding: 40%" />
                    <div class="d-flex" :style="{marginLeft: '5%'}">
                        <div>
                            <h3>#임시파일입니다</h3>
                            <h3>월~금 5시</h3>
                        </div>
                    </div>
                </div>
            </div>
        </v-row>
    </v-row>
</template>
<script>
export default {
    name: 'RecommandStudy',
    components: {}
};
</script>
