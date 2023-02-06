<template>
    <v-app>
        <div :key="item.comment_id" v-for="item in comments">
            {{ context }}
        </div>
    </v-app>
</template>
<script>
export default {
    name: 'CommuComment',
    props: {
        contentId: Number
    },
    data() {
        return {
            comments: [
                {
                    comment_id: 1,
                    user_id: 1,
                    content_id: 3,
                    context: '생일 축하해요!',
                    created_at: '2019-01-29 14:11:11',
                    updated_at: null
                },
                {
                    comment_id: 2,
                    user_id: 3,
                    content_id: 3,
                    context: '돌숭숭님 생일 축하해요!',
                    created_at: '2019-01-29 18:11:11',
                    updated_at: null
                },
                {
                    comment_id: 3,
                    user_id: 2,
                    content_id: 1,
                    context: '왕 돌숭숭',
                    created_at: '2019-03-29 14:11:11',
                    updated_at: null
                }
            ]
        };
    },
    methods: {}
};
</script>
<style scoped></style>
