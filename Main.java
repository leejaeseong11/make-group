class Group {
	private String[] people;
	private int eachGroupCount;

	public Group(String[] people, int eachGroupCount) {
		this.people = people;
		this.eachGroupCount = eachGroupCount;
	}

	public int getLittleGroupCount() {
		return eachGroupCount - this.people.length % eachGroupCount;
	}

	public int getGroupCount() {
		return this.people.length / eachGroupCount + (getLittleGroupCount() == 0 ? 0 : 1);
	}

	public String[][] makeGroup() {
		int peopleLength = this.people.length;
		String[][] returnedGroup = new String[this.getGroupCount()][this.eachGroupCount];
		boolean[] isVisited = new boolean[peopleLength];

		int cnt = 0;
		int row = 0;
		int col = 0;
		int littleGroupCount = this.getLittleGroupCount();
		while (cnt != peopleLength) {
			int index = (int) (Math.random() * peopleLength);

			if (isVisited[index]) {
				continue;
			}

			if (littleGroupCount != 0) {
				if (col < this.eachGroupCount - 1) {
					returnedGroup[returnedGroup.length - row - 1][col] = people[index];
					col++;
				} else {
					littleGroupCount -= 1;
					row++;
					col = 0;
					continue;
				}
			} else {
				if (col < this.eachGroupCount) {
					returnedGroup[returnedGroup.length - row - 1][col] = people[index];
					col++;
				} else {
					row++;
					col = 0;
					continue;
				}
			}

			isVisited[index] = true;
			cnt++;
		}
		return returnedGroup;
	}

	public static void print(String[][] group) {
		System.out.println("--------------------------------------------------");
		for (int i = 0; i < group.length; i++) {
			System.out.print(String.format("%d조: ", i + 1));
			for (int j = 0; j < group[i].length; j++) {
				System.out.print(group[i][j] != null ? String.format("%s ", group[i][j]) : "");
			}
			System.out.println();
		}
		System.out.println("--------------------------------------------------");

	}
}

public class Main {
	public static void main(String[] args) {
		// 전체 학생 목록 및 조 별 인원 수 변동 시 아래 수정
		String[] people = new String[] { "권수민", "김자성", "김태홍", "김학윤", "김현중", "김혜빈", "나원희", "백서현", "서재원", "송민우", "양도열",
				"오준택", "오찬석", "옥승호", "이단비", "이재성", "장오혁", "전세인", "정이새", "조홍식", "최윤경", "최하영" }; // 전체 학생 목록
		int eachGroupCount = 5; // 조 별(최대) 인원 수

		System.out.println("전체 인원 수: " + people.length);
		Group g = new Group(people, eachGroupCount);
		String[][] group = g.makeGroup();
		Group.print(group);
	}

}