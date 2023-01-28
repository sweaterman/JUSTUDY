<template>
    <div>
        <v-row justify="center">
            <v-col cols="8" align="center" md="4">
                <BasicButton :buttonLength="buttonLength" :height="height" :fontSize="fontSize" :content="`${content}`" :standard="`${standard}`" />
            </v-col>
            <v-col v-if="content === '이름'" cols="8" md="8" align="center">
                <h1>{{ text }}</h1>
            </v-col>
            <v-col v-else cols="16" md="8" align="center">
                <h1>{{ text }}</h1>
            </v-col>
        </v-row>
    </div>
</template>
<script>
import BasicButton from '@/components/common/BasicButton.vue';
export default {
    name: 'ButtonAndInput',
    components: {
        BasicButton
    },
    props: {
        buttonLength: {
            type: Number
        },
        height: {
            type: Number
        },
        fontSize: {
            type: Number
        },
        content: {
            type: String
        },
        standard: {
            type: String
        },
        text: {
            type: String
        }
    }
};
</script>
