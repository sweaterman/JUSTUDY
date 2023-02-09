<template>
    <div>
        <div v-if="!uploadImageFile">
            <v-img
                :src="src"
                :style="`
          width: ${diameter}${standard};
          height: ${diameter}${standard};
  
          border-radius: ${diameter / 2}${standard};
  
        `"
                v-on:click="dialogChange()"
            />
        </div>
        <div v-else>
            <v-img
                :src="uploadImageFile"
                :style="`
          width: ${diameter}${standard};
          height: ${diameter}${standard};
  
          border-radius: ${diameter / 2}${standard};
  
        `"
                v-on:click="dialogChange()"
            />
        </div>
        <label for="file">
            <v-img
                :src="require('@/assets/redHeart.png')"
                :style="`
          width: ${diameter / 3}${standard};
          height: ${diameter / 3}${standard};
          margin-top: ${-diameter / 3}${standard};
          margin-left: ${(2 * diameter) / 3}${standard};
          border-radius: ${diameter / 2}${standard};
        `"
            />
        </label>

        <input type="file" name="file" id="file" style="display: none" @change="onFileSelected" />
    </div>
</template>
<script>
export default {
    name: 'ProfilePicture',
    data() {
        return {
            file: null,
            uploadImageFile: null,
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
        },
        onFileSelected(event) {
            // console.log(event);

            var input = event.target;
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = e => {
                    this.uploadImageFile = e.target.result;
                    // console.log(input.files[0]);
                };
                reader.readAsDataURL(input.files[0]);
                this.file = input.files[0];
                this.$emit('sendData', input.files[0]);
            }
        }
    }
};
</script>
