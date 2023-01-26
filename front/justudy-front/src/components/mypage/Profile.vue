<template>
    <div>
        <v-img
            :src="src"
            :style="`
          width: ${diameter}${standard};
          height: ${diameter}${standard};
  
          border-radius: ${diameter / 2}${standard};
  
        `"
            v-on:click="dialogChange()"
        />
        <!-- <v-img
            :src="require('@/assets/redHeart.png')"
            :style="`
          width: ${diameter / 3}${standard};
          height: ${diameter / 3}${standard};
          margin-top: ${-diameter / 3}${standard};
          margin-left: ${(2 * diameter) / 3}${standard};
          border-radius: ${diameter / 2}${standard};
        `"
        /> -->
    </div>
</template>
<script>
export default {
    name: 'ProfilePicture',
    data() {
        return {
            aaa: 150
        };
    },
    props: {
        diameter: {
            type: Number
        },
        standard: {
            type: String
        },
        src: {}
    },
    methods: {
        dialogChange() {
            this.$emit('dialogChangeFromChild');
        }
    }
};
</script>
