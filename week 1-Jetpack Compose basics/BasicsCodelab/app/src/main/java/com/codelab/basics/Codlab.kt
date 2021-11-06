package com.codelab.basics

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelab.basics.ui.BasicsCodelabTheme
import kotlin.math.exp

//초기 Composition으로 view를 구성하고
//각각의 view는 recomposition을 통해 변경된 view만 update 하게 된다
//view를 변경하기 위해서는 remember라는 composable 객체를 사용하게 되면 state가 remember안에 메모리 형태로 저장되기 됨
//메모리에서 수정을 통해 mutableStateOf<T> 라는 걸 접근해서 value를 바꾸게됨
//단순히 variable을 통해 값을 변경되게 되면 state가 변경된게 아니라 뷰가 바뀌지 않음
class Codlab : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {//xml 대신 레이아웃을 구성할 Composable 함수가 들어갈 수 있다
            BasicsCodelabTheme {
                MyApp2()
            }
        }
    }
}

@Composable
private fun CardContent2(name : String){
    var expanded by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ){
        Column(modifier = Modifier
            .weight(1f)
            .padding(12.dp)) {
            Text(text = "Hello,")
            Text(
                text = name,
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded){
                Text(
                    text = "coposenf  sfeefe".repeat(4)
                )
            }
        }
        IconButton(onClick = {expanded = !expanded}) {
            Icon(imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = if (expanded) {
                stringResource(id = R.string.show_less)
            }else{
                stringResource(id = R.string.show_more)
            })
        }
    }
}


@Composable
fun OnboardingScreen2(onContinueClicked : () -> Unit){
    androidx.compose.material.Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("안녕 basics codelab이다!")
            Button(modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked) {
                Text(text = "Continue")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview2(){
    BasicsCodelabTheme {
        OnboardingScreen2(onContinueClicked = {})
    }
}

@Composable
private fun MyApp2(){

    var shouldShowOnboarding by rememberSaveable {
        mutableStateOf(true)
    }

    if (shouldShowOnboarding){
        OnboardingScreen2(onContinueClicked = {shouldShowOnboarding = false})
    }else{
        Greetings2()
    }

}

@Composable
private fun Greetings2(names : List<String> = List(10){"$it 번째"}){
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = names) {name->
            Greeting2(name = name)
        }
    }
}

@Composable // 이 어노테이션으로 컴포저블 함수로 인식이 가능하다
private fun Greeting2(name : String){
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ){
        CardContent2(name = name)
    }
}

@Preview(showBackground = true, widthDp = 320, uiMode = UI_MODE_NIGHT_YES, name = "DefaultPreviewDark")
@Composable
fun DefaultPreview2(){
    BasicsCodelabTheme {
        Greetings2()
    }
}
