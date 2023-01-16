<template>

    <div :style="`background-color : #FFD200 ; width : ${ buttonLength }${standard}; height: ${height}${standard};  border-radius: ${height/2}${standard} ` " class="d-flex justify-center align-center">
        <h1 :style="`font-size  : ${fontSize};`">{{content}}</h1>
    </div>

</template>
<script>
   export default {
      name : "YellowButton",
      props: {
        buttonLength : {
            type : Number
        },
        fontSize : {
            type : Number
        },
        height : {
            type : Number
        },
        content : {
            type : String
        },
        standard : {
            type : String
        }
      }
   }
</script>