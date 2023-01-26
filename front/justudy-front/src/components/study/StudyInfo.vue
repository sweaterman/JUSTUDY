<template>
    <v-container>
        <!-- 스터디 이름 -->
        <v-row>
            <v-col cols="2"> 스터디 </v-col>
            <v-col cols="10"> {{ study.name }} / {{ study.level }} </v-col>
        </v-row>
        <v-row>
            <v-col cols="2"> 주제 </v-col>
            <v-col cols="10"> {{ study.bottom_category }} </v-col>
        </v-row>
        <v-row>
            <v-col cols="2"> Github </v-col>
            <v-col cols="10"> {{ study.git_URL }} </v-col>
        </v-row>
        <v-row>
            <v-col cols="2"> Notion </v-col>
            <v-col cols="10"> {{ study.notion_URL }} </v-col>
        </v-row>
        <v-row>
            <v-col>
                <v-divider></v-divider>
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="2"> Description </v-col>
            <v-col cols="10"> {{ study.info }} </v-col>
        </v-row>
        <v-row>
            <v-col>
                <v-divider></v-divider>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
export default {
    name: 'StudyInfo',
    data() {
        return {
            study: {
                name: '리액트 용자 모임',
                level: '초보',
                promotion: true,
                bottom_category: 'React',
                git_URL: 'http://this.is.git.link',
                notion_URL: 'http://this.is.notion.link',
                info: '열심히하는스터디임니다.'
            }
        };
    }
};
</script>
