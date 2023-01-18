<template>
    <div>
        <v-row justify="center">
            <v-col cols="2" align="center">
                <YellowButton :buttonLength="`${buttonLength}`" :height="`${height}`" :fontSize="`${fontSize}`" :content="`${content}`" :standard="`${standard}`" />
            </v-col>
            <v-col cols="5">
                <v-select :items="items" label="Outlined style" outlined></v-select>
            </v-col>
        </v-row>
    </div>
</template>
<script>
import YellowButton from './YellowButton.vue';
export default {
    name: 'ButtonAndInput',
    components: {
        YellowButton
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
        }
    },
    data() {
        return {
            items: ['Foo', 'Bar', 'Fizz', 'Buzz']
        };
    }
};
</script>
