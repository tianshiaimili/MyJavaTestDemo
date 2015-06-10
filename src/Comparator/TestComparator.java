package Comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import DateTool.DateUtil;

public class TestComparator {

	public static void main(String[] args) throws Exception {
		
		TestAbstract testAbstract = new TestAbstract() {
			
			@Override
			public void getMoney() {
				
				System.out.println("laiba --------");
				
			}
		};
		
//		testAbstract.getAbstract();
		
//		sort();
		
	}

	public static void sort() throws Exception {
		List<Step> steps = new ArrayList<Step>();
		
		for(int i = 0;i<5;i++){
			
			Step step = new Step(DateUtil.format(new Date()), "ABC"+i);
			
			Thread.currentThread().sleep(2000);
			steps.add(step);
			
		}
		
		// 对集合对象进行排序
		StepComparator comparator = new StepComparator();
		Collections.sort(steps, comparator);
		if (steps != null && steps.size() > 0) {
			for (Step step : steps) {
				System.out.println(step.getAcceptAddress());
				System.out.println(step.getAcceptTime());
			}
		}

	}

}
