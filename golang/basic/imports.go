package main

// ✅ Factored Import (권장)
// import (
// 	"fmt"
// 	"math"
// 	"os"
// )
// - 깔끔하고 읽기 쉬움
// - Go 커뮤니티 표준

// ✅ Import 순서 관례
// - Go 표준: 알파벳순으로 정렬
// - fmt < math

// ✅ 패키지 함수 사용
// 패키지명.함수명()
// fmt.Println()   // fmt 패키지의 Println 함수
// math.Sqrt(7)    // math 패키지의 Sqrt 함수
import (
	"fmt"
	"math"
)

func main() {
	fmt.Println("Now you have %g problems.\n", math.Sqrt(7))
}
