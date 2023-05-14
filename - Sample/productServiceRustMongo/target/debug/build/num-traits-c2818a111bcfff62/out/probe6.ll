; ModuleID = 'probe6.564ce73d8acd3955-cgu.0'
source_filename = "probe6.564ce73d8acd3955-cgu.0"
target datalayout = "e-m:w-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-windows-msvc"

@alloc_9057f43742524f148e8d6982bdf28d5e = private unnamed_addr constant <{ [75 x i8] }> <{ [75 x i8] c"/rustc/39c6804b92aa202369e402525cee329556bc1db0\\library\\core\\src\\num\\mod.rs" }>, align 1
@alloc_1cd9f836e1baaf1f9f2ed2aee66fef3f = private unnamed_addr constant <{ ptr, [16 x i8] }> <{ ptr @alloc_9057f43742524f148e8d6982bdf28d5e, [16 x i8] c"K\00\00\00\00\00\00\00@\04\00\00\05\00\00\00" }>, align 8
@str.0 = internal constant [25 x i8] c"attempt to divide by zero"

; probe6::probe
; Function Attrs: uwtable
define void @_ZN6probe65probe17h756a9ce1a3e1908fE() unnamed_addr #0 {
start:
  %0 = call i1 @llvm.expect.i1(i1 false, i1 false)
  br i1 %0, label %panic.i, label %"_ZN4core3num21_$LT$impl$u20$u32$GT$10div_euclid17h48ff165dcafe4245E.exit"

panic.i:                                          ; preds = %start
; call core::panicking::panic
  call void @_ZN4core9panicking5panic17hdbbfbcb939ff87e1E(ptr align 1 @str.0, i64 25, ptr align 8 @alloc_1cd9f836e1baaf1f9f2ed2aee66fef3f) #3
  unreachable

"_ZN4core3num21_$LT$impl$u20$u32$GT$10div_euclid17h48ff165dcafe4245E.exit": ; preds = %start
  ret void
}

; Function Attrs: nocallback nofree nosync nounwind willreturn memory(none)
declare i1 @llvm.expect.i1(i1, i1) #1

; core::panicking::panic
; Function Attrs: cold noinline noreturn uwtable
declare void @_ZN4core9panicking5panic17hdbbfbcb939ff87e1E(ptr align 1, i64, ptr align 8) unnamed_addr #2

attributes #0 = { uwtable "target-cpu"="x86-64" }
attributes #1 = { nocallback nofree nosync nounwind willreturn memory(none) }
attributes #2 = { cold noinline noreturn uwtable "target-cpu"="x86-64" }
attributes #3 = { noreturn }

!llvm.module.flags = !{!0}

!0 = !{i32 8, !"PIC Level", i32 2}
