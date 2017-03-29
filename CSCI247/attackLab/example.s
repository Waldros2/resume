# Example of hand-generated assembly code
popq 	%rax  				#Push value onto stack	
movq	%rax,%rdi	                #Move  into %rdi
movq    $0x00401a74,(%rsp)		#Move into %rsp
retq 

