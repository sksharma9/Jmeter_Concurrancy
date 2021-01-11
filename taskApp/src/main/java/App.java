
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.glaucus.taskapp.model.User;
import com.glaucus.taskapp.repository.UserRepository;
 

public class App {

	
	public static void main(String[] args) {
		
		
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		
		final UserRepository ur= applicationContext.getBean(UserRepository.class,"userRepository");
		
	
		Runnable r1= new Runnable() {
			
			public void run() {
				
				System.out.println("<<thread starts>>"+ Thread.currentThread());
				ur.updateUserCount(8);
				System.out.println("<<thread ends>>"+ Thread.currentThread());
				
			}
		};
	
		Runnable r2= new Runnable() {
			
			public void run() {
				

				System.out.println("<<thread starts>>"+ Thread.currentThread());
				ur.updateUserCount(7);
				System.out.println("<<thread ends>>"+ Thread.currentThread());
				
			}
		};
		
		Runnable r3= new Runnable() {
			
			public void run() {
				
				System.out.println("<<thread starts>>"+ Thread.currentThread());
				ur.updateUserCount(6);
				System.out.println("<<thread ends>>"+ Thread.currentThread());
				
			}
		};
		for(int i=0;i<30;i++)
		{
			Thread t= new Thread(r1);
			t.start();
			System.out.println("t "+i+" "+"working");
		}
		
		
		
	}
	
	
	
}

