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
        <div>
            <ModalComponent :dialog="dialog" @closeModal="close" :id="seq" :src="src" :data="data" />
        </div>
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
import ModalComponent from './ModalComponent.vue';
export default {
    name: 'ProfilePicture',
    data() {
        return {
            dialog: false,
            aaa: 150
        };
    },
    components: {
        ModalComponent
    },
    props: {
        diameter: {
            type: Number
        },
        standard: {
            type: String
        },
        src: {},
        data: {},
        seq: {
            type: Number
        }
    },
    methods: {
        dialogChange() {
            this.dialog = true;
            this.$emit('dialogChangeFromChild');
        },
        close(status) {
            if (status) {
                this.dialog = false;
            }
        }
    }
};
</script>
