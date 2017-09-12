package com.memo.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

// *********** ArrayList == Database 
// Database�� model������ �����ؾ��Ѵ�
public class Memo_Model implements Memo_Model_I{
	
	private final String DB_DIR = "c:/workspaces/Java/database";
	private final String DB_FILE = "memo.txt";
	
	private File database = null;
	
	// Database�� ������ �ϴ� ArrayList
	ArrayList<Memo_Object> memoList = new ArrayList<Memo_Object> ();
	
	// ������ new������ ����Ǵ� ����
	public Memo_Model() {
		File dir = new File(DB_DIR);
		if(!dir.exists()) {
			dir.mkdirs(); // ��λ� ���丮�� ������ �ڵ�����
		}
		File file = new File(DB_DIR + File.separator + DB_FILE);
		if(!file.exists()) {
			try {
				file.createNewFile();
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		database = file;
	}
	
	private final String COLUMN_SEP = "::";
	
	// View���� �Է¹���  Memo_Object���¸� Controller���� �޾Ƽ�  ArrayList�� �ֱ�
	@Override
	public void createList(Memo_Object memo) {
		memo.setNo(memoList.size() + 1);
		
		try (FileOutputStream os = new FileOutputStream(database, true);){
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);
			
			String row = memo.getNo() + COLUMN_SEP + memo.getName() + COLUMN_SEP + memo.getContent() + COLUMN_SEP + memo.getDataTime() + "\n";
			
			bw.append(row);
			bw.flush();
			
		}catch(FileNotFoundException ex) {
			ex.printStackTrace();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
		memoList.add(memo);
	}

	// View���� �Է¹��� int ����  memo���� Controller���� �޾� ArrayList�� �ִ� �ش� memo�� ����
	@Override
	public void updateList(int number, Memo_Object memo) {
		memoList.get(number - 1).setContent(memo.getContent());
		memoList.get(number - 1).setDataTime(memo.getDataTime());
	}

	// View���� �Է¹��� int ���� Controller���� �޾� ArrayList���� memo�� ���� �� ArrayList�ȿ� �ִ� memo�� No���� ������
	@Override
	public void deleteList(int number) {

		memoList.remove(number - 1);
		for(int i = number - 1; i < memoList.size(); i++) {
			memoList.get(i).setNo(memoList.get(i).getNo() - 1);
		}
	}

	// ArrayList���� Controller�� ��ȯ���ش�.
	@Override
	public ArrayList<Memo_Object> getList() {
		// 1. �д� ��Ʈ���� ����.
		try(FileInputStream fis = new FileInputStream(database)) {
			// 2. ���� ���� ���ڵ��� �ٲ��ִ� ���� Ŭ������ ���
			InputStreamReader isr = new InputStreamReader (fis, "UTF-8");
			// 3. ����ó��
			BufferedReader br = new BufferedReader(isr);
			
			String row;
			// ���ο� ���� ���پ� �о row�� �����ϰ�
			// �� �̻� ���� �����Ͱ� ������ null �� ���ϵǹǷ� ������ ����ȴ�.
			
			while ((row=br.readLine()) !=null ) {
				
				String tempRow[] = row.split(COLUMN_SEP);
				// tempRow[0] = 1
				// tempRow[1] = 1
				// tempRow[2] = 1
				// tempRow[3] = 1
				
				/*
				Memo memo = new Memo();
				memo.no = Integer.parseInt(tempRow[0]);
				memo.name = Integer.parseInt(tempRow[1]);
				memo.content = Integer.parseInt(tempRow[2]);
				memo.datetime = Integer.parseInt(tempRow[3]);
				*/
				
				//list.add(memo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return memoList;
	}

	// View���� �Է¹��� int ���� Controller���� �޾� ArrayList�� ���� ���� �� Controller�� ��ȯ
	@Override
	public Memo_Object searchList(int number) {
		return memoList.get(number - 1);
	}


}